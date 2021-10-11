package com.zebas2.marvelapp.data.repository

import android.util.Log

import kotlinx.coroutines.async
import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.model.Data
import com.zebas2.marvelapp.data.repository.datasource.CharactersCacheDataSource
import com.zebas2.marvelapp.data.repository.datasource.CharactersLocalDataSource
import com.zebas2.marvelapp.data.repository.datasource.CharactersRemoteDataSource
import com.zebas2.marvelapp.data.util.Prefers
import com.zebas2.marvelapp.data.util.Resource
import com.zebas2.marvelapp.domain.repository.CharactersRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.lang.Exception

private const val TAG = "CharactersRepositoryImp"

class CharactersRepositoryImp(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val charactersCacheDataSource: CharactersCacheDataSource,
    private val prefers: Prefers
) : CharactersRepository {

    override suspend fun getCharacters(
        isUserNeededMoreCharacters: Boolean,
        isOfflineConnection: Boolean
    ): Resource<APIResponse> {
        return getCharactersFromCache(isUserNeededMoreCharacters, isOfflineConnection)
        //return getCharactersFromDB(isOfflineConnection)
    }

    override suspend fun getSearchedByNameCharacters(
        userSearch: String,
        isOfflineConnection: Boolean,
        isTemporalSearch: Boolean
    ): Resource<APIResponse> {
        return if (isOfflineConnection) {
            getSearchByNameCharactersFromDB(userSearch)
        } else {
            if (isTemporalSearch) {
                getStartSearchByNameCharactersFromAPI(userSearch)
            } else {
                getSearchByNameCharactersFromAPI(userSearch)
            }
        }
    }

    private suspend fun getSearchByNameCharactersFromAPI(userSearch: String): Resource<APIResponse> {
        return responseToResource(
            charactersRemoteDataSource.getSearchByNameCharacters(
                prefers.appCharacterLimit,
                0,
                userSearch
            )
        )
    }

    private suspend fun getStartSearchByNameCharactersFromAPI(userSearch: String): Resource<APIResponse> {
        return responseToResource(
            charactersRemoteDataSource.getStartSearchByNameCharacters(
                prefers.appCharacterLimit,
                0,
                userSearch
            )
        )
    }

    private suspend fun getSearchByNameCharactersFromDB(userSearch: String): Resource<APIResponse> {
        lateinit var apiResponse: APIResponse
        lateinit var response: Resource<APIResponse>
        val listSearch = charactersLocalDataSource.getSearchedByNameCharacters(userSearch)
        if (listSearch.isNotEmpty()) {
            apiResponse = APIResponse(Data(listSearch))
            response = Resource.Success(apiResponse)
        } else {
            response = Resource.Error("No se encontraron personajes relacionados")
        }
        return response
    }

    private suspend fun getCharactersFromAPI(): Resource<APIResponse> {
        val response: Resource<APIResponse> =
            responseToResource(
                charactersRemoteDataSource.getCharacters(
                    prefers.appCharacterLimit,
                    prefers.appCharacterOffset
                )
            )

        if (response is Resource.Success) {
            prefers.appCharacterTotal = response.data?.data?.total!!.toInt()
            prefers.sumLimitToOffset()
            prefers.sumLimitToOffsetDB()
            response.data?.data?.results?.let { charactersLocalDataSource.saveCharacters(it) }
        }

        return response
    }

    private suspend fun getCharactersFromDB(isOfflineConnection: Boolean): Resource<APIResponse> {
        lateinit var apiResponse: APIResponse
        lateinit var response: Resource<APIResponse>
        try {
            val characterList = charactersLocalDataSource.getCharactersFromOffset(
                prefers.appCharacterLimit,
                prefers.appCharacterOffsetDB
            )
            if (characterList.isNotEmpty()) {
                prefers.sumLimitToOffsetDB()
                apiResponse = APIResponse(Data(characterList))
                response = Resource.Success(apiResponse)
                return response
            } else {
                response = if (isOfflineConnection) {
                    Resource.Error("Verifique su conexion a internet")
                } else {
                    getCharactersFromAPI()
                }
            }
        } catch (e: Exception) {
            Log.i(TAG, "getCharactersFromDB: ${e.message.toString()}")
            response = Resource.Error("Error en el sistema, intente mas tarde")
        }
        return response
    }

    private suspend fun getCharactersFromCache(
        isUserNeededMoreCharacters: Boolean,
        isOfflineConnection: Boolean
    ): Resource<APIResponse> {
        lateinit var apiResponse: APIResponse
        lateinit var response: Resource<APIResponse>
        lateinit var characterList: List<Character>

        try {
            characterList = charactersCacheDataSource.getCharactersFromCache()
        } catch (e: Exception) {
            Log.i(TAG, "getCharactersFromDB: ${e.message.toString()}")
            response = Resource.Error("Error en el sistema, intente mas tarde")
            return response
        }

        if ((characterList.size == prefers.appCharacterOffsetDB && isUserNeededMoreCharacters) || characterList.isEmpty()) {
            response = getCharactersFromDB(isOfflineConnection)
            if (response is Resource.Success) {
                charactersCacheDataSource.saveCharactersToCache(response.data!!.data.results)
                response.data!!.data.results = charactersCacheDataSource.getCharactersFromCache()
            }
        } else {
            apiResponse = APIResponse(
                Data(characterList)
            )
            response = Resource.Success(apiResponse)
            return response
        }
        return response
    }

    override fun getCharactersPage(limit: Int, offset: Int): Resource<APIResponse> {
        return responseToResource(charactersRemoteDataSource.getCharactersPage(limit, offset))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    override suspend fun getCharacterDetail(characterId: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    override fun getSaveCharacter(): Flow<List<Character>> {
        TODO("Not yet implemented")
    }

}