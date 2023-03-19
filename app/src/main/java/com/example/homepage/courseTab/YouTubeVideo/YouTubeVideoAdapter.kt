package com.example.homepage.courseTab.YouTubeVideo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.Model.YouTubeVideoData
import com.example.homepage.databinding.CardYoutubeVideosBinding
import com.example.homepage.recyclerViewHolder.YouTubeVideoViewHolder

class YouTubeVideoAdapter : RecyclerView.Adapter<YouTubeVideoViewHolder>() {

    private val itemList = ArrayList<YouTubeVideoData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YouTubeVideoViewHolder {
        val binding =
            CardYoutubeVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return YouTubeVideoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: YouTubeVideoViewHolder, position: Int) {
        holder.bind(itemList[position])
    }



    override fun getItemCount(): Int {
        return itemList.size
    }


    fun updateVideoList(userList: List<YouTubeVideoData>) {

        this.itemList.clear()
        this.itemList.addAll(userList)
        notifyDataSetChanged()

    }



}