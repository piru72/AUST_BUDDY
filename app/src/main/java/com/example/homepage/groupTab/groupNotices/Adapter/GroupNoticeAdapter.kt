package com.example.homepage.groupTab.groupNotices.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.groupTab.groupNotices.Model.GroupNoticeData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GroupNoticeAdapter : RecyclerView.Adapter<GroupNoticeAdapter.GroupNoticeViewHolder>() {
    private val tasks = ArrayList<GroupNoticeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupNoticeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_group_notices,
            parent, false
        )
        return GroupNoticeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupNoticeViewHolder, position: Int) {

        val auth = Firebase.auth
        val user = auth.currentUser!!.uid

        val currentTask = tasks[position]

        holder.taskName.text = currentTask.taskName
        holder.taskDescription.text = currentTask.taskDescription
        holder.taskDate.text = currentTask.taskDate

    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun updateNotices(tasks: List<GroupNoticeData>) {

        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()

    }

    class GroupNoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskName: TextView = itemView.findViewById(R.id.taskNameCard)
        val taskDescription: TextView = itemView.findViewById(R.id.taskDescriptionCard)
        val taskDate: TextView = itemView.findViewById(R.id.taskDateCard)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

    }

}