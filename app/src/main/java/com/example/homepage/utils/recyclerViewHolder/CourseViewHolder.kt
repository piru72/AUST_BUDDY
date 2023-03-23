package com.example.homepage.utils.recyclerViewHolder

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardCoursesBinding
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.utils.helpers.ItemViewHelper
import com.example.homepage.utils.models.CourseData

class CourseViewHolder(private val binding: CardCoursesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(courseData: CourseData, userType: String) {
        binding.tvCourseCode.text = courseData.courseCode
        binding.tvCourseName.text = courseData.courseName

        setupButtonVisibility(userType)
        setupButtonFunctionality(courseData)


    }

    private fun setupButtonVisibility(userType: String) {
        if (userType == "user") {
            binding.courseDeclineButton.visibility = View.GONE
            binding.courseApproveButton.visibility = View.GONE
        }
    }

    private fun setupButtonFunctionality(courseData: CourseData) {


        val firebaseHelper = ChildUpdaterHelper()
        val itemViewHelper = ItemViewHelper(itemView.context)

        binding.courseDeclineButton.setOnClickListener {

            firebaseHelper.declineCourseRequest(courseData)
            itemViewHelper.makeToast("Course request has been declined")

        }
        binding.courseApproveButton.setOnClickListener {

            firebaseHelper.approveCourseRequest(courseData)
            itemViewHelper.makeToast("Course request has been approved")

        }

        binding.exploreButton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse(courseData.driveLink))
            itemView.context.startActivity(i)
        }
        binding.shareButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, courseData.driveLink)
            itemView.context.startActivity(Intent.createChooser(intent, "Share"))
        }

    }


}