package com.zebas2.marvelapp.di

import com.zebas2.marvelapp.data.repository.CharactersRepositoryImp
import com.zebas2.marvelapp.data.repository.datasource.CharactersCacheDataSource
import com.zebas2.marvelapp.data.repository.datasource.CharactersLocalDataSource
import com.zebas2.marvelapp.data.repository.datasource.CharactersRemoteDataSource
import com.zebas2.marvelapp.data.util.Prefers
import com.zebas2.marvelapp.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCharactersRepository(
        charactersRemoteDataSource: CharactersRemoteDataSource,
        charactersLocalDataSource: CharactersLocalDataSource,
        charactersCacheDataSource: CharactersCacheDataSource,
        prefers: Prefers
    ): CharactersRepository {
        return CharactersRepositoryImp(
            charactersRemoteDataSource,
            charactersLocalDataSource,
            charactersCacheDataSource,
            prefers
        )
    }

}