package com.example.androidProjectOnTFLite

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_capture_photo.*
import java.io.ByteArrayOutputStream


class CapturePhotoFragment : Fragment() {
    lateinit var Camera: ImageView
    lateinit var Button: Button
    lateinit var intent:Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(
            intent,
            CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capture_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        Button = btnCapturePhoto
        Camera = imgViewCamera

        Button.setOnClickListener {
                startActivityForResult(
                    intent,
                    CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
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
                // convert byte array to Bitmap
                val bitmap = BitmapFactory.decodeByteArray(
                    byteArray, 0,
                    byteArray.size
                )
                Camera.setImageBitmap(bitmap)
            }
        }
    }

    companion object {
        private const val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888
    }


}
