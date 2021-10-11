package com.zebas2.marvelapp.data.repository.datasource

import com.zebas2.marvelapp.data.model.APIResponse
import retrofit2.Response

interface CharactersRemoteDataSource {

    suspend fun getCharacters(
        limit: Int,
        offset: Int
    ): Response<APIResponse>

    suspend fun getSearchByNameCharacters(
        limit: Int,
        offset: Int,
        name: String
    ): Response<APIResponse>

    suspend fun getStartSearchByNameCharacters(
        limit: Int,
        offset: Int,
        nameStartsWith: String
    ): Response<APIResponse>

    fun getCharactersPage(limit: Int, offset: Int): Response<APIResponse>

}