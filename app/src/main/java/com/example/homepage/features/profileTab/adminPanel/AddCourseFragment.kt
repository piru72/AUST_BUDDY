package com.example.homepage.features.profileTab.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.homepage.utils.models.CourseData
import com.example.homepage.databinding.FragmentAddCourseBinding
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.utils.helpers.ReplaceFragment
import com.example.homepage.utils.helpers.ValidationHelper


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
            val department = spinnerDepartmentList.selectedItem.toString()
            val year = spinnerYearList.selectedItem.toString()
            val semester = spinnerSemesterList.selectedItem.toString()
            val course = extractForm()

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

    private fun extractForm(): CourseData {
        val course: CourseData
        viewBinding.apply {
            val courseCode = courseCode.text.toString()
            val courseName = courseName.text.toString()
            val courseDriveLink = courseDriveLinkText.text.toString()
            val department = spinnerDepartmentList.selectedItem.toString()
            val year = spinnerYearList.selectedItem.toString()
            val semester = spinnerSemesterList.selectedItem.toString()
            val yearSemester = "year" + year + "semester" + semester
            val requestingPath = "$department/$yearSemester/$courseCode"
            course = CourseData(courseCode, courseName, courseDriveLink, requestingPath)
        }

        return course
    }


    private fun clearForm() {

        viewBinding.apply {
            courseCode.setText("")
            courseDriveLinkText.setText("")
            courseName.setText("")
        }
    }


}