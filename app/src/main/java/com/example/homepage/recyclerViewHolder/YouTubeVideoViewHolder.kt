package com.example.homepage.recyclerViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homepage.Model.YouTubeVideoData
import com.example.homepage.databinding.CardYoutubeVideosBinding
import com.example.homepage.helperClass.ItemViewHelper

class YouTubeVideoViewHolder(private val binding: CardYoutubeVideosBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(youTubeData: YouTubeVideoData) {
        binding.title.text = youTubeData.title
        Glide.with(itemView.context).load(youTubeData.thumbnail).into(binding.thumbnail)


        setupButtonFunctionality(youTubeData)


    }

    private fun setupButtonFunctionality(youTubeData: YouTubeVideoData) {
        val itemViewHelper = ItemViewHelper(itemView.context)

        binding.btnSeeVideo.setOnClickListener {
            itemViewHelper.makeToast("Watching Video")

        }
    }
}