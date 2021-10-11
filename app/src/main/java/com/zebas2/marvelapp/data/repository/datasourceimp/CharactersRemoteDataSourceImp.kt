package com.zebas2.marvelapp.data.repository.datasourceimp

import com.zebas2.marvelapp.BuildConfig
import com.zebas2.marvelapp.data.api.MarvelAPIService
import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.repository.datasource.CharactersRemoteDataSource
import com.zebas2.marvelapp.data.util.RequestParams
import retrofit2.Response

class CharactersRemoteDataSourceImp(
    private val marvelAPIService: MarvelAPIService,
    private val requestParams: RequestParams
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(limit: Int, offset: Int): Response<APIResponse> {
        val ts = requestParams.getTimeStamp()
        return marvelAPIService.getCharacters(
            limit,
            offset,
            ts,
            BuildConfig.API_PUBLIC_KEY,
            requestParams.getMd5Hash(ts)
        )
    }

    override suspend fun getSearchByNameCharacters(
        limit: Int,
        offset: Int,
        name: String
    ): Response<APIResponse> {
        val ts = requestParams.getTimeStamp()
        return marvelAPIService.getSearchByNameCharacters(
            limit,
            offset,
            ts,
            BuildConfig.API_PUBLIC_KEY,
            requestParams.getMd5Hash(ts),
            name
        )
    }

    override suspend fun getStartSearchByNameCharacters(
        limit: Int,
        offset: Int,
        nameStartsWith: String
    ): Response<APIResponse> {
        val ts = requestParams.getTimeStamp()
        return marvelAPIService.getStartSearchByNameCharacters(
            limit,
            offset,
            ts,
            BuildConfig.API_PUBLIC_KEY,
            requestParams.getMd5Hash(ts),
            nameStartsWith
        )
    }

    override fun getCharactersPage(limit: Int, offset: Int): Response<APIResponse> {
        val ts = requestParams.getTimeStamp()
        return marvelAPIService.getCharactersPage(
            limit,
            offset,
            ts,
            BuildConfig.API_PUBLIC_KEY,
            requestParams.getMd5Hash(ts)
        )
    }

}