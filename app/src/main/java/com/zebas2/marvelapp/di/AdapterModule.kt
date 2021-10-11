package com.zebas2.marvelapp.di

import com.zebas2.marvelapp.presentation.adapter.CharacterAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideCharacterAdapter(): CharacterAdapter {
        return CharacterAdapter()
    }

}