package com.zebas2.marvelapp.data.repository.datasource

import com.zebas2.marvelapp.data.model.Character

interface CharactersLocalDataSource {

    suspend fun getCharacters(): List<Character>
    suspend fun getCharactersFromOffset(limit: Int, offset: Int): List<Character>
    suspend fun saveCharacters(characters: List<Character>)
    suspend fun getSearchedByNameCharacters(userSearch: String): List<Character>
    suspend fun clearAll()

}