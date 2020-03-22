package com.example.androidProjectOnTFLite.RoomDB

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "Picture")
class Picture(
    @field:ColumnInfo(name = "image_net_classes") var imageNetClasses: String,
    @field:ColumnInfo(name = "img_path") var imgPath: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}