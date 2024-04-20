package com.example.movieapp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.core.database.converters.Converters
import com.example.movieapp.core.database.dao.AnimeDao
import com.example.movieapp.core.database.dao.AnimeDaoMyList
import com.example.movieapp.core.database.dao.AnimeDaoTopCharacters
import com.example.movieapp.core.database.dao.AnimeDaoTopSearches
import com.example.movieapp.core.database.entities.AnimeItemMyList
import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.database.entities.AnimeItemTopSearches

@Database(entities = [
        AnimeItemTopHits::class,
        AnimeItemTopCharacters::class,
        AnimeItemMyList::class,
        AnimeItemTopSearches::class],
    version = 6,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animedao(): AnimeDao
    abstract fun animedaoTopCharacters(): AnimeDaoTopCharacters
    abstract fun animedaoMyList(): AnimeDaoMyList
    abstract fun animedaoTopSearches(): AnimeDaoTopSearches


    companion object{
        private var db: AnimeDatabase? = null

        fun getInstance(context: Context): AnimeDatabase {
            if(db == null){
                db = Room.databaseBuilder(
                    context,
                    AnimeDatabase::class.java, "anime-database.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return db!!
        }
    }
}