package com.example.androidProjectOnTFLite

import android.database.Observable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.androidProjectOnTFLite.RoomDB.AppDatabase
import com.example.androidProjectOnTFLite.RoomDB.Picture
import com.example.androidProjectOnTFLite.RoomDB.PictureDao

var db: AppDatabase? = null
var PictureDao: PictureDao? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "pics-database").allowMainThreadQueries()
            .build()
    }
}
