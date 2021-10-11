package com.zebas2.marvelapp.di

import android.app.Application
import androidx.room.Room
import com.zebas2.marvelapp.data.db.CharacterDao
import com.zebas2.marvelapp.data.db.CharacterDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideCharactersDataBase(
        app: Application
    ): CharacterDataBase {
        return Room.databaseBuilder(app, CharacterDataBase::class.java, "characters_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(characterDataBase: CharacterDataBase): CharacterDao {
        return characterDataBase.getCharacterDao()
    }

}