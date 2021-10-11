package com.zebas2.marvelapp.di

import android.app.Application
import com.zebas2.marvelapp.domain.usecase.GetCharactersUseCase
import com.zebas2.marvelapp.domain.usecase.GetSearchedCharactersUseCase
import com.zebas2.marvelapp.presentation.viewmodel.CharactersViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideCharactersViewModelFactory(
        application: Application,
        getCharactersUseCase: GetCharactersUseCase,
        getSearchedCharactersUseCase: GetSearchedCharactersUseCase
    ): CharactersViewModelFactory {
        return CharactersViewModelFactory(
            application,
            getCharactersUseCase,
            getSearchedCharactersUseCase
        )
    }

}