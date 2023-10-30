package com.example.compose_compose.viewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_NAME = "compose-compose"

@Database(entities = [Tree::class], version = 1, exportSchema = false)
abstract class CCDatabase: RoomDatabase() {
    abstract fun ccDao():CCDao
    companion object{
        @Volatile private var instance:CCDatabase?=null
        fun getInstance(context: Context):CCDatabase{
            return instance?: synchronized(this){
                instance?: buildDatabase(context).also {
                    instance = it
                }
            }

        }

        private fun buildDatabase(context:Context):CCDatabase{
            return Room.databaseBuilder(context,CCDatabase::class.java,DATABASE_NAME)
                .build()
        }

    }

}