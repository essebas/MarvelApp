package com.zebas2.marvelapp.domain.repository

import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharacters(
        isUserNeededMoreCharacters: Boolean,
        isOfflineConnection: Boolean
    ): Resource<APIResponse>

    suspend fun getCharacterDetail(characterId: String): Resource<APIResponse>

    suspend fun getSearchedByNameCharacters(
        userSearch: String,
        isOfflineConnection: Boolean,
        isTemporalSearch: Boolean
    ): Resource<APIResponse>

    suspend fun saveCharacter(character: Character)
    fun getCharactersPage(limit: Int, offset: Int): Resource<APIResponse>
    suspend fun deleteCharacter(character: Character)
    fun getSaveCharacter(): Flow<List<Character>>

}