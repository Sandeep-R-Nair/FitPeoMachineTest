package com.fitpeo.machinetest.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.machinetest.R
import com.fitpeo.machinetest.databinding.RecyclerPhotosRowBinding
import com.fitpeo.machinetest.models.PhotosResponse
import com.fitpeo.machinetest.views.DetailsActivity
import com.squareup.picasso.Picasso

class PhotosListAdapter (ctx: Context, list: PhotosResponse?) : RecyclerView.Adapter<PhotosListAdapter.PhotosViewHolder?>() {
    private var context: Context? = null
    private var photosList: PhotosResponse?
    private var binding: RecyclerPhotosRowBinding? = null

    init {
        photosList = list
        context = ctx
    }

    fun updatePhotosList(list: PhotosResponse?) {
        photosList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        binding = RecyclerPhotosRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.binding.textTitle.text = photosList!![position].title
        Picasso.get()
            .load(photosList!![position].thumbnailUrl)
            .into(binding!!.imageView)

        holder.binding.root.setOnClickListener{
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("title",photosList!![position].title)
            intent.putExtra("image",photosList!![position].url)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (photosList != null) photosList!!.size else 0
    }

    inner class PhotosViewHolder(itemBinding: RecyclerPhotosRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var binding: RecyclerPhotosRowBinding

        init {
            this.binding = itemBinding
        }
    }
}
