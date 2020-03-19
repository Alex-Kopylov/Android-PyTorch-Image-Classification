package com.example.androidProjectOnTFLite.RoomDB

import androidx.room.*
@Dao
interface PictureDao {
    @Query("SELECT * FROM Picture")
    fun getPictures(): List<Picture>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicture(pic: Picture?)

    @Delete
    fun delete(pic: Picture?)

    @Query("SELECT * FROM Picture WHERE id = :picId")
    fun getPicById(picId: Int): Picture?

    @Query("SELECT * FROM Picture WHERE id IN (:picIds)")
    fun loadAllByIds(picIds: IntArray?): List<Picture?>?

}