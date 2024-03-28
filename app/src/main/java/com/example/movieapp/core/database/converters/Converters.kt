package com.example.movieapp.core.database.converters

import androidx.room.TypeConverter
import com.example.movieapp.core.network.models.topHits.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromGenresList(genres: List<Genre>): String {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().toJson(genres, type)
    }

    @TypeConverter
    fun toGenresList(genresString: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(genresString, type)
    }
}
