package com.e444er.wall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.e444er.wall.databinding.ItemViewBinding
import com.e444er.wall.model.Data
import com.e444er.wall.util.BlurHashDecoder


class RecyclerViewAdapter() :
    PagingDataAdapter<Data, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {


    inner class MyViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        return MyViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        val image = getItem(position)
        val blur = BlurHashDecoder.blurHashBitmap(holder.itemView.resources, image)
        holder.binding.apply {
            Glide.with(root)
                .asBitmap()
                .load(image?.fullImageUrl)
                .centerCrop()
                .error(blur)
                .transition(BitmapTransitionOptions.withCrossFade(80))
                .placeholder(blur)
                .into(imageView)
        }
    }


    class DiffUtilCallBack : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.blurHash == newItem.blurHash

        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem

        }
    }
}