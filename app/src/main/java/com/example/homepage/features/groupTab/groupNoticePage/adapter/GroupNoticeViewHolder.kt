package com.example.homepage.features.groupTab.groupNoticePage.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.database.FirebaseUtils
import com.example.homepage.databinding.CardQuizScheduleBinding
import com.example.homepage.utils.helpers.CustomFormatters
import com.example.homepage.utils.models.GroupNoticeData
import java.time.LocalDate

class GroupNoticeViewHolder(private val binding: CardQuizScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val user = FirebaseUtils.getUserId()


    fun bind(noticeData: GroupNoticeData, _inflater: LayoutInflater) {

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

        binding.btnDeleteSchedule.setOnClickListener {
            val childValue = noticeData.groupId.toString()

            val parentNode = "group-notice/$childValue"
            val childNode = noticeData.path.toString()

            ChildUpdaterHelper().removeChild(parentNode, childNode)
        }


        binding.btnEditSchedule.setOnClickListener {

            EditScheduleClickListener(
                _inflater,
                noticeData
            )
        }


    }

}