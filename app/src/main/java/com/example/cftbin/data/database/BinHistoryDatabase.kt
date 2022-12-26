package com.example.cftbin.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BinHistoryItemDbModel::class], version = 1, exportSchema = false)
abstract class BinHistoryDatabase: RoomDatabase() {
    abstract fun binListDao(): BinListDao

    companion object {
        private var INSTANCE: BinHistoryDatabase? = null
        private val LOCK = Any()

        private const val DB_NAME = "bin_history_item"

        fun getInstance(application: Application): BinHistoryDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }

                val database = Room.databaseBuilder(
                    application,
                    BinHistoryDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = database
                return database
            }
        }
    }
}