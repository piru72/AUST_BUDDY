package com.example.homepage.features.homeTab.favouriteWebPage.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardFavouriteWebpageBinding
import com.example.homepage.utils.models.FavouriteWebpageData


class FavouriteWebAdapter : RecyclerView.Adapter<FavouriteWebpageViewHolder>() {
    private val _itemList = ArrayList<FavouriteWebpageData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteWebpageViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = CardFavouriteWebpageBinding.inflate(inflater, parent, false)
        return FavouriteWebpageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteWebpageViewHolder, position: Int) {
        val currentItem = _itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    fun updateWebPageList(tasks: List<FavouriteWebpageData>) {

        this._itemList.clear()
        this._itemList.addAll(tasks)
        notifyDataSetChanged()

    }


}