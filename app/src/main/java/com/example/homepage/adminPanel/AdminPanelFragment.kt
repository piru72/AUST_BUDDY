package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentAdminPanelBinding
import com.example.homepage.helperClass.ReplaceFragment


class AdminPanelFragment : ReplaceFragment() {
    private var fragmentBinding: FragmentAdminPanelBinding? = null
    private val viewBinding get() = fragmentBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentAdminPanelBinding.inflate(inflater, container, false)

        setupButtons()

        return viewBinding.root
    }

    private fun setupButtons() {

        viewBinding.apply{

            btnAddCoursesAdmin.setOnClickListener {
                val action =
                    AdminPanelFragmentDirections.actionAdminPanelFragmentToAddCourseFragment(
                        "course-list",
                        "view"
                    )
                findNavController().navigate(action)
            }

            btnAddTeachersAdmin.setOnClickListener {
                val action =
                    AdminPanelFragmentDirections.actionAdminPanelFragmentToAddTeachersFragment(
                        "teachers",
                        "view"
                    )
                findNavController().navigate(action)
            }

            btnTeacherRequest.setOnClickListener {
                val action =
                    AdminPanelFragmentDirections.actionAdminPanelFragmentToTeachersFragment(
                        "admin-teacher-request-list",
                        "view"
                    )
                findNavController().navigate(action)
            }

            btnViewBugReports.setOnClickListener {
                val action =
                    AdminPanelFragmentDirections.actionAdminPanelFragmentToBugReportListFragment()
                findNavController().navigate(action)
            }
            btnViewCourseRequest.setOnClickListener {
                val action = AdminPanelFragmentDirections.actionAdminPanelFragmentToViewCourses2(
                    "admin-course-request-list",
                    "view"
                )
                findNavController().navigate(action)
            }

            btnViewAdminRequest.setOnClickListener {
                val action =
                    AdminPanelFragmentDirections.actionAdminPanelFragmentToAdminRequestFragment3()
                findNavController().navigate(action)
            }
        }
    }

}