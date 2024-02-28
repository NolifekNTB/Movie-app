package com.example.movieapp.Home.logic.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.Home.data.AnimeItem

@Database(entities = [AnimeItem::class], version = 1)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animedao(): AnimeDao

    companion object{
        private var db: AnimeDatabase? = null

        fun getInstance(context: Context): AnimeDatabase {
            if(db == null){
                db = Room.databaseBuilder(
                    context,
                    AnimeDatabase::class.java, "anime-database.db"
                ).build()
            }

            return db!!
        }
    }
}