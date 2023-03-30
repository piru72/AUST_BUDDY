package com.example.homepage.features.groupTab.groupNoticePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.database.FirebaseDateComparator
import com.example.homepage.databinding.CardQuizScheduleBinding
import com.example.homepage.utils.models.GroupNoticeData
import java.util.*


class GroupNoticeAdapter(inflater: LayoutInflater) :
    RecyclerView.Adapter<GroupNoticeViewHolder>() {

    private val _itemList = ArrayList<GroupNoticeData>()
    private var _inflater: LayoutInflater = inflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupNoticeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardQuizScheduleBinding.inflate(inflater, parent, false)
        return GroupNoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupNoticeViewHolder, position: Int) {
        holder.bind(_itemList[position], _inflater)
    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    fun updateGroupNoticeList(notices: List<GroupNoticeData>) {
        Collections.sort(notices, FirebaseDateComparator())
        this._itemList.clear()
        this._itemList.addAll(notices)
        notifyDataSetChanged()

    }

}