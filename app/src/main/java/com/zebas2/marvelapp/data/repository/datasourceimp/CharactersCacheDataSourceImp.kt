package com.zebas2.marvelapp.data.repository.datasourceimp

import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.repository.datasource.CharactersCacheDataSource

class CharactersCacheDataSourceImp : CharactersCacheDataSource {

    private var characterList = ArrayList<Character>()

    override suspend fun getCharactersFromCache(): List<Character> = characterList

    override suspend fun saveCharactersToCache(listCharacter: List<Character>) {
        characterList.addAll(listCharacter)
    }

    override suspend fun clearCharactersToCache() {
        characterList.clear()
    }
}