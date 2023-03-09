package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.homepage.courseTab.Model.CourseData
import com.example.homepage.databinding.FragmentAddCourseBinding
import com.example.homepage.helperClass.Firebase.ChildUpdaterHelper
import com.example.homepage.helperClass.ReplaceFragment
import com.example.homepage.helperClass.ValidationHelper


class AddCourseFragment : ReplaceFragment() {
    private lateinit var fragmentBinding: FragmentAddCourseBinding
    private val viewBinding get() = fragmentBinding

    private val args: AddCourseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentAddCourseBinding.inflate(inflater, container, false)

        setupButtons()

        return viewBinding.root
    }

    private fun setupButtons() {
        val pushingPath = args.reference

        with(viewBinding.addCourseButtonForm)
        {
            if (pushingPath == "admin-course-request-list")
                text = "Request for adding course"
            setOnClickListener {
                validateForm()
            }

        }
    }


    private fun validateForm() {

        viewBinding.apply {
            val courseCode = courseCode.text.toString()
            val courseName = courseName.text.toString()
            val courseDriveLink = courseDriveLinkText.text.toString()
            val department = spinnerDepartmentList.selectedItem.toString()
            val year = spinnerYearList.selectedItem.toString()
            val semester = spinnerSemesterList.selectedItem.toString()
            val yearSemester = "year" + year + "semester" + semester
            val requestingPath = "$department/$yearSemester/$courseCode"
            val course = CourseData(courseCode, courseName, courseDriveLink, requestingPath)

            val adminHelper = ValidationHelper()
            val childUpdater = ChildUpdaterHelper()
            val verdict = adminHelper.validateCourseForm(department, year, semester, course)

            when {
                verdict != "Valid Data" -> makeToast(verdict)
                else -> {
                    childUpdater.writeNewCourse(course, args.reference)
                    makeToast("Request sent to Admins!")
                    clearForm()

                }
            }
        }

    }

    private fun clearForm() {

        viewBinding.apply {
            courseCode.setText("")
            courseDriveLinkText.setText("")
            courseName.setText("")
        }
    }


}