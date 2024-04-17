package com.example.movieapp.core.di

import android.app.Application
import com.example.movieapp.features.Home.data.repositories.AnimeRepository
import com.example.movieapp.features.Home.data.repositories.AnimeRepositoryTopCharacters
import com.example.movieapp.features.MyList.data.AnimeRepositoryMyList
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
    fun provideContextTopHits(app: Application): AnimeRepository {
        return AnimeRepository(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideContextNewSeasons(app: Application): AnimeRepositoryTopCharacters {
        return AnimeRepositoryTopCharacters(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideContextMyList(app: Application): AnimeRepositoryMyList {
        return AnimeRepositoryMyList(app.applicationContext)
    }
}