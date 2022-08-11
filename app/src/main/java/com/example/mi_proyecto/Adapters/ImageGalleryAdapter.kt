package com.example.mi_proyecto.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.ImageGallery
import kotlinx.android.synthetic.main.recycler_gallery.view.*

class ImageGalleryAdapter(val items : ArrayList<ImageGallery>, val context: Context) : RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>()  {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_gallery, parent, false))
    }

    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvItemImageUrl = view.itemImage

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageUrl : String = items.get(position).imageUrl
        Glide
            .with(context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into((holder?.tvItemImageUrl))


    }


}