package com.movieapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.movieapp.R
import com.movieapp.domainmodel.Trending
import com.movieapp.ui.main.view.MainActivity
import com.movieapp.utils.Constants.Companion.IMAGE_URL
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TrendingAdapter @ExperimentalCoroutinesApi constructor(val list : List<Trending>, val mContext : MainActivity) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.row_inflate_trending, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Trending =list[position]
        holder.tvName.text=model.originalName
        holder.tvDescription.text=model.overview
        holder.tvRating.text="Rating ${model.voteAvegare}"
        holder.tvCount.text="Vote  ${model.voteCount}"

        Glide
            .with(mContext)
            .load(IMAGE_URL+model.imagepath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.imgPoster);

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster=itemView.findViewById(R.id.poster_image)as AppCompatImageView
        val tvName=itemView.findViewById(R.id.text_tv_name)as AppCompatTextView
        val tvDescription=itemView.findViewById(R.id.text_tv_description)as AppCompatTextView
        val tvRating=itemView.findViewById(R.id.text_rating)as AppCompatTextView
        val tvCount=itemView.findViewById(R.id.text_count)as AppCompatTextView



    }
}