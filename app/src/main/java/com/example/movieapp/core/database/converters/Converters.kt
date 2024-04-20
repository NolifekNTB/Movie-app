package com.example.movieapp.core.database.converters

import androidx.room.TypeConverter
import com.example.movieapp.core.network.models.shared.Trailer
import com.example.movieapp.core.network.models.shared.Genre
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

    @TypeConverter
    fun fromTrailer(trailer: Trailer?): String {
        if (trailer == null) {
            return ""
        }
        val gson = Gson()
        val type = object : TypeToken<Trailer>() {}.type
        return gson.toJson(trailer, type)
    }

    @TypeConverter
    fun toTrailer(trailerString: String?): Trailer? {
        if (trailerString == null || trailerString.isEmpty()) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Trailer>() {}.type
        return gson.fromJson(trailerString, type)
    }
}
