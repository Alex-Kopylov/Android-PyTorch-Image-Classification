package com.example.androidProjectOnTFLite.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Picture::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao?

    companion object {
        private var mInstance: AppDatabase? = null
        private const val DATABASE_NAME = "pics-database"

        @Synchronized

        fun getDatabaseInstance(context: Context): AppDatabase? {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance
        }

        fun destroyInstance() {
            mInstance = null
        }
    }
}