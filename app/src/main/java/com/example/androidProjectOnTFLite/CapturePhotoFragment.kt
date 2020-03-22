package com.example.androidProjectOnTFLite

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidProjectOnTFLite.RoomDB.Picture
import com.example.androidProjectOnTFLite.Utils.Utils
import kotlinx.android.synthetic.main.fragment_capture_photo.*
import java.io.ByteArrayOutputStream


class CapturePhotoFragment : Fragment() {
    private lateinit var camera: TextView
    private lateinit var btnCapturePhoto: Button
    private val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    private lateinit var textClass:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startCamera()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnCapturePhoto = btn_capture_photo
        camera = img_view_camera
        textClass = text_view_class
        btnCapturePhoto.setOnClickListener {
            startCamera()
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val bmp = data!!.extras!!["data"] as Bitmap?
                val stream = ByteArrayOutputStream()
                bmp!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray: ByteArray = stream.toByteArray()
                val bitmap = BitmapFactory.decodeByteArray(
                    byteArray, 0,
                    byteArray.size
                )
                camera.text=""
                camera.setBackgroundDrawable((BitmapDrawable(resources, bitmap)))
                textClass.text = "Classifying..."

                val imageNetClasses = ImageClassification(bitmap, context!!).objectDetection()
                textClass.text = imageNetClasses
                btnCapturePhoto.visibility=View.VISIBLE

                val imgPath = Utils.getImagePathFromBitmap(context!!, bitmap, title = imageNetClasses)
                db?.pictureDao()?.insertPicture(Picture(imageNetClasses, imgPath))
            }
            else {
                camera.text = ""
                camera.background = resources.getDrawable(R.drawable.pytorch_logo)
                btnCapturePhoto.visibility = View.VISIBLE
            }
        }
    }
    private fun startCamera(){
        startActivityForResult(
            intent,
            CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
    }
    companion object {
        private const val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888
    }


}
