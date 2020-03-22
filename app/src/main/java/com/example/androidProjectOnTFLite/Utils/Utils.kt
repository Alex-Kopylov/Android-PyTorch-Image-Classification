package com.example.androidProjectOnTFLite.Utils

import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    fun assetGetAbsolutePathByName(context: Context, assetName: String): String? {
        val file = File(context.filesDir, assetName)
        try {
            context.assets.open(assetName).use { `is` ->
                FileOutputStream(file).use { os ->
                    val buffer = ByteArray(4 * 1024)
                    var read: Int
                    while (`is`.read(buffer).also { read = it } != -1) {
                        os.write(buffer, 0, read)
                    }
                    os.flush()
                }
                return file.absolutePath
            }
        } catch (e: IOException) {
            Log.e("pytorchandroid", "Error process asset $assetName to file path")
        }
        return null
    }

    fun getImagePathFromBitmap(context: Context, bitmap: Bitmap, title: String = "Title", description: String? = null): String {
        val bytes = ByteArrayOutputStream()
        val sdf =  SimpleDateFormat("-dd.MM.yyyy.HH-mm-ss")
        val fileName = title + sdf.format(Date())
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        return MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, fileName, description)
    }

}