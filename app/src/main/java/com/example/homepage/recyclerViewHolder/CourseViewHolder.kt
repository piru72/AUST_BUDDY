package com.example.homepage.recyclerViewHolder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R

class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // The values for user_item variable are created here and assigned above
    val courseCode: TextView = itemView.findViewById(R.id.tvCourseCode)
    val courseName: TextView = itemView.findViewById(R.id.tvCourseName)
    val exploreButton: Button = itemView.findViewById(R.id.exploreButton)
    val shareButton: Button = itemView.findViewById(R.id.shareButton)
    val approveButton: Button = itemView.findViewById(R.id.courseApproveButton)
    val declineButton: Button = itemView.findViewById(R.id.courseDeclineButton)

}