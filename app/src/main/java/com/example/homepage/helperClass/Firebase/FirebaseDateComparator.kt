package com.example.homepage.helperClass.Firebase

import com.example.homepage.Model.GroupNoticeData
import java.text.SimpleDateFormat
import java.util.*

class FirebaseDateComparator : Comparator<GroupNoticeData?> {
    override fun compare(p0: GroupNoticeData?, p1: GroupNoticeData?): Int {

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        val firstDate: Date = dateFormat.parse(p0?.taskdate!!) as Date
        val secondDate: Date = dateFormat.parse(p1?.taskdate!!) as Date

        return firstDate.compareTo(secondDate)
    }
}