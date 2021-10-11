package com.zebas2.marvelapp.di

import com.zebas2.marvelapp.BuildConfig
import com.zebas2.marvelapp.data.api.MarvelAPIService
import com.zebas2.marvelapp.data.util.RequestParams
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun MarvelAPIService(retrofit: Retrofit): MarvelAPIService {
        return retrofit.create(MarvelAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRequestParams(): RequestParams {
        return RequestParams()
    }

}