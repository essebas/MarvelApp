package com.zebas2.marvelapp.di

import com.zebas2.marvelapp.data.repository.datasource.CharactersCacheDataSource
import com.zebas2.marvelapp.data.repository.datasourceimp.CharactersCacheDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {

    @Singleton
    @Provides
    fun provideCacheDataSource(): CharactersCacheDataSource {
        return CharactersCacheDataSourceImp()
    }

}