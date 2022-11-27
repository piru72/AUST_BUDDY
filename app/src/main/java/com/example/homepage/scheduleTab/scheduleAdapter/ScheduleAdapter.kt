package com.example.homepage.scheduleTab.scheduleAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.scheduleTab.scheduleModel.ScheduleData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewViewHolder>() {
    private val tasks = ArrayList<ScheduleData>()
    private val taskIds = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_quiz_schedule,
            parent, false
        )
        return ScheduleViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduleViewViewHolder, position: Int) {

        val auth = Firebase.auth
        val user = auth.currentUser!!.uid
        val taskReference =
            FirebaseDatabase.getInstance().getReference("user-tasks").child(user)

        val currentTask = tasks[position]

        holder.taskName.text = currentTask.taskname
        holder.taskDescription.text = currentTask.taskdescription
        holder.taskDate.text = currentTask.taskdate

        holder.deleteButton.setOnClickListener {
//            val value = taskReference.child(taskIds[position])
//            value.removeValue()
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun updateUserList(tasks: List<ScheduleData>) {

        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()

    }

    class ScheduleViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskName: TextView = itemView.findViewById(R.id.taskNameCard)
        val taskDescription: TextView = itemView.findViewById(R.id.taskDescriptionCard)
        val taskDate: TextView = itemView.findViewById(R.id.taskDateCard)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

    }

}