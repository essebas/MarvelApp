package com.zebas2.marvelapp.domain.repository

import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharacters(): Resource<APIResponse>
    suspend fun getCharacterDetail(characterId: String): Resource<APIResponse>

    suspend fun saveCharacter(character: Character)
    suspend fun deleteCharacter(character: Character)
    fun getSaveCharacter():Flow<List<Character>>

}