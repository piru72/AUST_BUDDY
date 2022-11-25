package com.example.homepage.groupTab.groupNotices.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.groupTab.groupNotices.Model.GroupNoticeData


class GroupNoticeAdapter : RecyclerView.Adapter<GroupNoticeAdapter.GroupNoticeViewHolder>() {
    private val notices = ArrayList<GroupNoticeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupNoticeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_group_notices,
            parent, false
        )
        return GroupNoticeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupNoticeViewHolder, position: Int) {

        val currentNotice = notices[position]

        holder.noticeName.text = currentNotice.taskName
        holder.noticeDescription.text = currentNotice.taskDescription
        holder.noticeDate.text = currentNotice.taskDate

    }

    override fun getItemCount(): Int {
        return notices.size
    }

    fun updateNotices(notices: List<GroupNoticeData>) {

        this.notices.clear()
        this.notices.addAll(notices)
        notifyDataSetChanged()

    }

    class GroupNoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noticeName: TextView = itemView.findViewById(R.id.taskNameCardNotice)
        val noticeDescription: TextView = itemView.findViewById(R.id.taskDescriptionCardNotice)
        val noticeDate: TextView = itemView.findViewById(R.id.taskDateCardNotice)

    }

}