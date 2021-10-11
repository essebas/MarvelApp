package com.zebas2.marvelapp.di

import com.zebas2.marvelapp.domain.repository.CharactersRepository
import com.zebas2.marvelapp.domain.usecase.GetCharactersUseCase
import com.zebas2.marvelapp.domain.usecase.GetSearchedCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCharactersUseCase(
        charactersRepository: CharactersRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedCharactersUseCase(
        charactersRepository: CharactersRepository
    ): GetSearchedCharactersUseCase {
        return GetSearchedCharactersUseCase(charactersRepository)
    }

}