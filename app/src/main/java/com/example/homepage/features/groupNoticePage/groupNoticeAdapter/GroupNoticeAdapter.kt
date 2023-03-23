package com.example.homepage.features.groupNoticePage.groupNoticeAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardQuizScheduleBinding
import com.example.homepage.utils.models.GroupNoticeData
import com.example.homepage.utils.helpers.CustomFormatters
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.database.FirebaseDateComparator
import com.example.homepage.database.FirebaseUtils
import java.time.LocalDate
import java.util.*


class GroupNoticeAdapter(inflater: LayoutInflater) :
    RecyclerView.Adapter<GroupNoticeAdapter.ScheduleViewViewHolder>() {


    private val tasks = ArrayList<GroupNoticeData>()
    private var _inflater: LayoutInflater = inflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewViewHolder {
        val binding =
            CardQuizScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ScheduleViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun updateGroupNoticeList(notices: List<GroupNoticeData>) {
        Collections.sort(notices, FirebaseDateComparator())
        this.tasks.clear()
        this.tasks.addAll(notices)
        notifyDataSetChanged()

    }


    inner class ScheduleViewViewHolder(private val binding: CardQuizScheduleBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        val user = FirebaseUtils.getUserId()

        init {
            binding.btnDeleteSchedule.setOnClickListener(this)
            binding.btnEditSchedule.setOnClickListener(this)
        }

        fun bind(noticeData: GroupNoticeData) {

            binding.taskNameCard.text = noticeData.taskname
            binding.taskDescriptionCard.text = noticeData.taskdescription
            binding.taskDateCard.text = noticeData.taskdate

            val dateStr = noticeData.taskdate.toString()
            val date1 = CustomFormatters().formatDateString(dateStr)

            if (date1.isBefore(LocalDate.now())) {
                val childValue = noticeData.groupId.toString()

                val parentNode = "group-notice/$childValue"
                val childNode = noticeData.path.toString()

                ChildUpdaterHelper().removeChild(parentNode, childNode)

            }

            if (user != noticeData.uid) {
                binding.btnDeleteSchedule.visibility = View.INVISIBLE
                binding.btnEditSchedule.visibility = View.INVISIBLE
            }
        }

        override fun onClick(view: View?) {

            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                val noticeData = tasks[position]

                when (view) {
                    binding.btnDeleteSchedule -> {
                        val childValue = noticeData.groupId.toString()

                        val parentNode = "group-notice/$childValue"
                        val childNode = noticeData.path.toString()

                        ChildUpdaterHelper().removeChild(parentNode, childNode)
                    }


                    binding.btnEditSchedule -> {

                        EditScheduleClickListener(
                            _inflater,
                            noticeData
                        ).onClick(view)
                    }


                }
            }

        }
    }
}