package com.example.homepage.features.adminPanel.adminRequest.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.utils.models.Admin
import com.example.homepage.databinding.CardAdminRequestBinding
import com.example.homepage.utils.recyclerViewHolder.AdminRequestViewHolder

class AdminRequestAdapter : RecyclerView.Adapter<AdminRequestViewHolder>() {


    private val itemList = ArrayList<Admin>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminRequestViewHolder {
        val binding =
            CardAdminRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AdminRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminRequestViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    fun updateAdminRequestList(newItemList: List<Admin>) {

        this.itemList.clear()
        this.itemList.addAll(newItemList)
        notifyDataSetChanged()

    }


}