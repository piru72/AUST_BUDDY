package com.example.homepage.features.groupTab.Group.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardUserGroupsBinding
import com.example.homepage.utils.models.GroupData

class UserGroupAdapter(inflater: LayoutInflater) :
    RecyclerView.Adapter<UserGroupViewHolder>() {
    private val _itemList = ArrayList<GroupData>()
    private var _inflater: LayoutInflater = inflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardUserGroupsBinding.inflate(inflater, parent, false)
        return UserGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserGroupViewHolder, position: Int) {
        val currentGroup = _itemList[position]
        holder.bind(currentGroup, _inflater)
    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    fun updateUserGroupList(groupData: List<GroupData>) {

        this._itemList.clear()
        this._itemList.addAll(groupData)
        notifyDataSetChanged()
    }


}