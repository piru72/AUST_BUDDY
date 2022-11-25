package com.example.homepage.groupTab.groupNotices.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.groupTab.groupNotices.Model.GroupNoticeData

class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    private val notices = ArrayList<GroupNoticeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_group_notices,
            parent, false
        )
        return NoticeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val currentNotice = notices[position]
        holder.noticeName.text = currentNotice.taskName
    }


    override fun getItemCount(): Int {
        return notices.size
    }

    fun updateNoticeList(notice: List<GroupNoticeData>) {

        this.notices.clear()
        this.notices.addAll(notice)
        notifyDataSetChanged()

    }

    class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noticeName: TextView = itemView.findViewById(R.id.NoticeNameCard)

    }
}