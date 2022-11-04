package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.courseTab.Model.CourseData
import com.example.homepage.databinding.FragmentAddCourseBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddCourseFragment : ReplaceFragment() {
    private lateinit var _binding:FragmentAddCourseBinding
    private val binding get() = _binding
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentAddCourseBinding.inflate(inflater, container, false)
        database = Firebase.database.reference
        binding.addCourseButtonForm.setOnClickListener{
            val courseCode = binding.courseCode.text.toString()
            val courseName= binding.courseName.text.toString()
            val courseDriveLink = binding.courseDriveLinkText.text.toString()
            writeNewCourse(courseCode,courseName,courseDriveLink )

        }
        return binding.root
    }

    private fun writeNewCourse(courseCode: String, courseName: String, courseDriveLink: String) {

        val newCourse = CourseData( courseCode, courseName, courseDriveLink)
        val taskValues = newCourse.toMap()
        val childUpdates = hashMapOf<String, Any>(
        "/Courses/$courseCode" to taskValues
    )
        database.updateChildren(childUpdates)
    }



}