package com.example.movieapp.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.Home.data.room.topHits.AnimeDao
import com.example.movieapp.Home.data.room.newSeasons.AnimeDaoNewSeasons
import com.example.movieapp.Home.data.room.newSeasons.AnimeItemNewSeasons
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import com.example.movieapp.MyList.data.AnimeDaoMyList
import com.example.movieapp.MyList.data.AnimeItemMyList

@Database(
    entities = [
        AnimeItemTopHits::class,
        AnimeItemNewSeasons::class,
        AnimeItemMyList::class
               ],
    version = 3)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animedao(): AnimeDao
    abstract fun animedaoNewSeasons(): AnimeDaoNewSeasons
    abstract fun animedaoMyList(): AnimeDaoMyList


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