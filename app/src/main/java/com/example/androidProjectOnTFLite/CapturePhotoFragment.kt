package com.example.androidProjectOnTFLite

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
    private lateinit var camera: ImageView
    private lateinit var btnCapturePhoto: Button
    private lateinit var intent:Intent
    private lateinit var textClass:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(
            intent,
            CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capture_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        btnCapturePhoto = btn_capture_photo
        camera = img_view_camera
        textClass = text_view_class
        btnCapturePhoto.setOnClickListener {
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
                camera.setImageBitmap(bitmap)
                textClass.text = "Loading..."
                val imageNetClasses = ImageClassification(bitmap, context!!).objectDetection()
                textClass.text = imageNetClasses
                btnCapturePhoto.visibility=View.VISIBLE

                Thread {
                    val imgPath =
                        Utils.getImagePathFromBitmap(context!!, bitmap, title = imageNetClasses)
                    PictureDao?.insertPicture(Picture(imageNetClasses, imgPath))

                    //textClass.text = PictureDao!!.getPictures().toString()
                    db?.pictureDao()?.getPictures()?.forEach() {
                        Log.i("Fetch Records", "Id:  : ${it.id}")
                        Log.i("Fetch Records", "Classes:  : ${it.imageNetClasses}")
                        Log.i("Fetch Records", "Path:  : ${it.imgPath}")
                    }
                }.start()
            }
        }
    }

    companion object {
        private const val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888
    }


}
