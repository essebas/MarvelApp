package com.zebas2.marvelapp.data.repository.datasource

import com.zebas2.marvelapp.data.model.Character

interface CharactersCacheDataSource {

    suspend fun getCharactersFromCache(): List<Character>
    suspend fun saveCharactersToCache(listCharacter: List<Character>)
    suspend fun clearCharactersToCache()

}