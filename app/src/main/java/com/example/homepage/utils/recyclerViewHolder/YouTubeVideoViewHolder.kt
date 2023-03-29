package com.example.homepage.utils.recyclerViewHolder

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homepage.utils.models.YouTubeVideoData
import com.example.homepage.databinding.CardYoutubeVideosBinding
import com.example.homepage.features.homeTab.courseTab.YouTubeVideo.YouTubeVideoFragmentDirections

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