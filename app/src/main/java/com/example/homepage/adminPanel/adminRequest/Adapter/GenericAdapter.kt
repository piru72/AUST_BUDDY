package com.example.homepage.adminPanel.adminRequest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T, VH : RecyclerView.ViewHolder>(
    private val viewHolderClass: Class<VH>,
    private val layoutResId: Int,
    private val onBind: (holder: VH, item: T) -> Unit
) : RecyclerView.Adapter<VH>() {

    private val itemList = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        val constructor = viewHolderClass.getConstructor(View::class.java)
        return constructor.newInstance(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBind(holder, itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(newItemList: List<T>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

}