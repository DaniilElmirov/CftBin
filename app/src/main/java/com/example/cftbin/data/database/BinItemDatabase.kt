package com.example.cftbin.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BinItemDbModel::class], version = 1, exportSchema = false)
abstract class BinItemDatabase: RoomDatabase() {
    abstract fun binListDao(): BinListDao

    companion object {
        private var INSTANCE: BinItemDatabase? = null
        private val LOCK = Any()

        private const val DB_NAME = "bin_history_item"

        fun getInstance(application: Application): BinItemDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }

                val database = Room.databaseBuilder(
                    application,
                    BinItemDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = database
                return database
            }
        }
    }
}