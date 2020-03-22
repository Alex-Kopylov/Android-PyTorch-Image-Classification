package com.example.androidProjectOnTFLite

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidProjectOnTFLite.RoomDB.Picture


class ItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)){

    private var classes: TextView? = null
    private var photo: ImageView? = null

    init{
        classes = itemView.findViewById(R.id.classesTextView)
        photo = itemView.findViewById(R.id.photo)
    }

    fun bind(pic: Picture){
        photo?.setImageURI(Uri.parse(pic.imgPath))
        classes?.text = pic.imageNetClasses
    }
}