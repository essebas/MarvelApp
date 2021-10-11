package com.zebas2.marvelapp.di

import com.zebas2.marvelapp.data.db.CharacterDao
import com.zebas2.marvelapp.data.repository.datasource.CharactersLocalDataSource
import com.zebas2.marvelapp.data.repository.datasourceimp.CharactersLocalDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideDataSource(characterDao: CharacterDao): CharactersLocalDataSource {
        return CharactersLocalDataSourceImp(characterDao)
    }

}