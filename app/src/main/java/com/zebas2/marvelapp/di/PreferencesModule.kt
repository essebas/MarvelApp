package com.zebas2.marvelapp.di

import android.app.Application
import com.zebas2.marvelapp.data.util.Prefers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Singleton
    @Provides
    fun providePreferences(app: Application): Prefers {
        return Prefers(app)
    }

}