package com.example.homepage.features.plazaTab.plazaDashboard.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardPlazaDashboardBinding
import com.example.homepage.utils.models.Announcements
import com.example.homepage.utils.recyclerViewHolder.PlazaDashBoardViewHolder


class PlazaDashBoardAdapter(inflater: LayoutInflater) :
    RecyclerView.Adapter<PlazaDashBoardViewHolder>() {

    private val _itemList = ArrayList<Announcements>()
    private var _inflater: LayoutInflater = inflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlazaDashBoardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardPlazaDashboardBinding.inflate(inflater, parent, false)
        return PlazaDashBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlazaDashBoardViewHolder, position: Int) {
        val currentItem = _itemList[position]
        holder.bind(currentItem, _inflater)
    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    fun updatePlazaDashboardList(materials: List<Announcements>) {

        this._itemList.clear()
        this._itemList.addAll(materials)
        notifyDataSetChanged()

    }


}