package com.zebas2.marvelapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zebas2.marvelapp.data.model.Character

@Database(
    entities = [Character::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class CharacterDataBase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

}