package com.zebas2.marvelapp.data.repository.datasourceimp

import com.zebas2.marvelapp.data.db.CharacterDao
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.repository.datasource.CharactersLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersLocalDataSourceImp(private val characterDao: CharacterDao) :
    CharactersLocalDataSource {

    override suspend fun getCharacters(): List<Character> = characterDao.getCharacters()

    override suspend fun getCharactersFromOffset(limit: Int, offset: Int): List<Character> =
        characterDao.getCharactersFromOffset(limit, offset)

    override suspend fun saveCharacters(characters: List<Character>) {
        CoroutineScope(Dispatchers.IO).launch {
            characterDao.insert(characters)
        }
    }

    override suspend fun getSearchedByNameCharacters(userSearch: String): List<Character> =
        characterDao.getSearchedByNameCharacter(userSearch)


    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            characterDao.deleteAllCharacters()
        }
    }

}