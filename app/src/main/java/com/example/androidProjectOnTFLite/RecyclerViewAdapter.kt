package com.example.androidProjectOnTFLite

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidProjectOnTFLite.RoomDB.Picture
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter(val items: MutableList<Picture>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageURI(Uri.parse(items[position].imgPath))
        holder.classes.text = items[position].imageNetClasses
        holder.delete.setOnClickListener { deleteItem(position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun deleteItem(position: Int){
        db?.pictureDao()?.delete(items[position]) //from db
        items.removeAt(position) //from list
        notifyItemRemoved(position) //from screen
        notifyItemRangeChanged(position, itemCount)
    }
    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.photo
        val classes: TextView = itemView.classesTextView
        val delete: ImageButton = itemView.btnDelete
    }
}