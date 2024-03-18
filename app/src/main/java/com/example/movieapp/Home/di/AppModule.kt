package com.example.movieapp.Home.di

import android.app.Application
import com.example.movieapp.Home.data.room.AnimeRepository
import com.example.movieapp.Home.data.room.AnimeRepositoryNewSeasons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): AnimeRepository {
        return AnimeRepository(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideContextNewSeasons(app: Application): AnimeRepositoryNewSeasons {
        return AnimeRepositoryNewSeasons(app.applicationContext)
    }
}