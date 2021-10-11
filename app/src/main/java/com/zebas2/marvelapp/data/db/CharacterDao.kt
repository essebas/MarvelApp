package com.zebas2.marvelapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zebas2.marvelapp.data.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: List<Character>)

    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<Character>

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM characters WHERE idOffset > :offset LIMIT :limit")
    suspend fun getCharactersFromOffset(limit: Int, offset: Int): List<Character>

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :userSearch || '%'")
    suspend fun getSearchedByNameCharacter(userSearch: String): List<Character>

}