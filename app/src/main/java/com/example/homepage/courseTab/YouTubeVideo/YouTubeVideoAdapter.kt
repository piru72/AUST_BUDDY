package com.example.homepage.courseTab.YouTubeVideo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.Model.YouTubeVideoData
import com.example.homepage.databinding.CardYoutubeVideosBinding
import com.example.homepage.helperClass.Firebase.FirebaseTitleComparator
import com.example.homepage.recyclerViewHolder.YouTubeVideoViewHolder
import java.util.*

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


    fun updateVideoList(newList: List<YouTubeVideoData>) {
        Collections.sort(newList , FirebaseTitleComparator())
        this.itemList.clear()
        this.itemList.addAll(newList)
        notifyDataSetChanged()

    }



}