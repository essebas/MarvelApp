package com.zebas2.marvelapp.di

import com.zebas2.marvelapp.data.api.MarvelAPIService
import com.zebas2.marvelapp.data.repository.datasource.CharactersRemoteDataSource
import com.zebas2.marvelapp.data.repository.datasourceimp.CharactersRemoteDataSourceImp
import com.zebas2.marvelapp.data.util.RequestParams
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideCharactersRemoteDataSource(
        marvelAPIService: MarvelAPIService,
        requestParams: RequestParams
    ): CharactersRemoteDataSource {
        return CharactersRemoteDataSourceImp(marvelAPIService, requestParams)
    }

}