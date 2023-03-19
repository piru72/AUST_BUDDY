package com.example.homepage.recyclerViewHolder

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homepage.Model.YouTubeVideoData
import com.example.homepage.courseTab.YouTubeVideo.YouTubeVideoFragmentDirections
import com.example.homepage.databinding.CardYoutubeVideosBinding

class YouTubeVideoViewHolder(private val binding: CardYoutubeVideosBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(youTubeData: YouTubeVideoData) {
        binding.title.text = youTubeData.title
        Glide.with(itemView.context).load(youTubeData.thumbnail).into(binding.thumbnail)


        setupButtonFunctionality(youTubeData)


    }

    private fun setupButtonFunctionality(youTubeData: YouTubeVideoData) {
        itemView.setOnClickListener { v ->
            val videoId = youTubeData.videoId.toString()
            val action = YouTubeVideoFragmentDirections.actionYouTubeVideoFragmentToYoutubeFragment(
                videoId,
                "reference"
            )
            val navController = Navigation.findNavController(v)
            navController.navigate(action)
        }
    }
}