package com.example.homepage.database

import com.example.homepage.utils.models.GroupNoticeData
import com.example.homepage.utils.models.YouTubeVideoData
import java.text.DateFormat
import java.util.*

class FirebaseDateComparator : Comparator<GroupNoticeData?> {
    override fun compare(p0: GroupNoticeData?, p1: GroupNoticeData?): Int {

        val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)

        val firstDate: Date = dateFormat.parse(p0?.taskdate!!) as Date
        val secondDate: Date = dateFormat.parse(p1?.taskdate!!) as Date

        return firstDate.compareTo(secondDate)
    }
}


class FirebaseTitleComparator : Comparator<YouTubeVideoData?> {
    override fun compare(p0: YouTubeVideoData?, p1: YouTubeVideoData?): Int {

        val firstTitle = p0?.title
        val secondTitle = p1?.title

        return firstTitle?.compareTo(secondTitle ?: "") ?: 0

    }
}