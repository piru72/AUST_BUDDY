package com.example.homepage.features.plazaTab.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardAnnouncementsBinding
import com.example.homepage.utils.models.Announcements

class PlazaAdapter : RecyclerView.Adapter<AnnouncementViewHolder>() {
    private val _itemList = ArrayList<Announcements>()


    // Attaching the layout that will hold the announcements and populate them in the recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardAnnouncementsBinding.inflate(inflater, parent, false)
        return AnnouncementViewHolder(binding)
    }

    // Attaching the data with the card
    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val currentItem = _itemList[position]
        holder.bind(currentItem)
    }


    // Total child
    override fun getItemCount(): Int {
        return _itemList.size
    }


    // Realtime update of the announcements
    fun updateAnnouncementList(materials: List<Announcements>) {

        this._itemList.clear()
        this._itemList.addAll(materials)
        notifyDataSetChanged()

    }



}