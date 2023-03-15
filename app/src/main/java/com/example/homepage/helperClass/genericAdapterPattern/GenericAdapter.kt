package com.example.homepage.helperClass.genericAdapterPattern

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T, VH : RecyclerView.ViewHolder, Binding : ViewDataBinding>(
    private val viewHolderClass: Class<VH>,
    private val bindingClass: Class<Binding>,
    private val onBind: (holder: VH, item: T) -> Unit
) : RecyclerView.Adapter<VH>() {

    private val itemList = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
            .invoke(null, inflater, parent, false) as Binding
        val constructor = viewHolderClass.getConstructor(bindingClass)
        return constructor.newInstance(binding)
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