package com.zebas2.marvelapp.data.api

import com.zebas2.marvelapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPIService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int,
        @Query("ts")
        ts: String,
        @Query("apikey")
        apiKey: String,
        @Query("hash")
        hash: String,
    ): Response<APIResponse>

    @GET("/v1/public/characters")
    suspend fun getSearchByNameCharacters(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int,
        @Query("ts")
        ts: String,
        @Query("apikey")
        apiKey: String,
        @Query("hash")
        hash: String,
        @Query("name")
        name: String
    ): Response<APIResponse>

    @GET("/v1/public/characters")
    suspend fun getStartSearchByNameCharacters(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int,
        @Query("ts")
        ts: String,
        @Query("apikey")
        apiKey: String,
        @Query("hash")
        hash: String,
        @Query("nameStartsWith")
        nameStartsWith: String
    ): Response<APIResponse>

    @GET("/v1/public/characters")
    fun getCharactersPage(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int,
        @Query("ts")
        ts: String,
        @Query("apikey")
        apiKey: String,
        @Query("hash")
        hash: String,
    ): Response<APIResponse>

}