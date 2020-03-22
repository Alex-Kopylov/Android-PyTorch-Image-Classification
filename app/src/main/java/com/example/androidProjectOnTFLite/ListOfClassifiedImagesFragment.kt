package com.example.androidProjectOnTFLite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidProjectOnTFLite.RoomDB.Picture
import kotlinx.android.synthetic.main.fragment_list_of_classified_images.*

class ListOfClassifiedImagesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_classified_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            recyclerView.apply{
                layoutManager = LinearLayoutManager(activity)
                adapter = RecyclerViewAdapter((db?.pictureDao()?.getPictures() as MutableList<Picture>?)!!)
            }

    }
}
