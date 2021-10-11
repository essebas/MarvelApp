package com.zebas2.marvelapp.marvelapiclient.data.api

import com.google.common.truth.Truth
import com.zebas2.marvelapp.BuildConfig
import com.zebas2.marvelapp.data.api.MarvelAPIService
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.util.RequestParams
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class CharactersAPIServiceTest {

    private lateinit var service: MarvelAPIService
    private lateinit var server: MockWebServer
    private lateinit var requestParams: RequestParams

    @Before
    fun setUp() {
        requestParams = RequestParams()
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }


    @Test
    fun getCharacters_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("charactersResponse.json")
            val responseBody = service.getCharacters(
                "1",
                BuildConfig.API_PUBLIC_KEY,
                "ffd275c5130566a2916217b101f26150"
            ).body()
            val request = server.takeRequest()
            Truth.assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path)
                .isEqualTo("/v1/public/characters?ts=1&apikey=982d5307d197953b745b10c4dfbe2967&hash=ffd275c5130566a2916217b101f26150")
        }
    }

    @Test
    fun getCharacters_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse("charactersResponse.json")
            val responseBody = service.getCharacters(
                "1",
                BuildConfig.API_PUBLIC_KEY,
                "ffd275c5130566a2916217b101f26150"
            ).body()
            val charactersList = responseBody!!.data.count
            Truth.assertThat(charactersList).isEqualTo("20")
        }
    }

    @Test
    fun getCharacters_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("charactersResponse.json")
            val responseBody = service.getCharacters(
                "1",
                BuildConfig.API_PUBLIC_KEY,
                "ffd275c5130566a2916217b101f26150"
            ).body()
            val character = responseBody!!.data.results[0]
            Truth.assertThat(character.id).isEqualTo("1011334")
            Truth.assertThat(character.name).isEqualTo("3-D Man")
            Truth.assertThat(character.thumbnail.path)
                .isEqualTo("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784")
            Truth.assertThat(character.comics.available).isEqualTo("12")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}