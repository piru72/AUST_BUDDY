package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.adminPanel.bugReports.BugReportListFragment
import com.example.homepage.adminPanel.courseRequest.CourseRequestListFragment
import com.example.homepage.adminPanel.teacherRequest.AddTeacherListFragment
import com.example.homepage.databinding.FragmentAdminPanelBinding
import com.example.homepage.superClass.ReplaceFragment


class AdminPanelFragment : ReplaceFragment() {
 private var  _binding: FragmentAdminPanelBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        _binding = FragmentAdminPanelBinding.inflate(inflater, container, false)
        binding.btnAddCoursesAdmin.setOnClickListener{

            replaceFragment(AddCourseFragment("course-list"), R.id.fragment_admin_panel)
        }
        binding.btnAddTeachersAdmin.setOnClickListener{

            replaceFragment(AddTeachersFragment("teachers"), R.id.fragment_admin_panel)
        }
        binding.btnTeacherRequest.setOnClickListener{
            replaceFragment(AddTeacherListFragment(),R.id.fragment_admin_panel)
        }
        binding.btnViewBugReports.setOnClickListener{
            replaceFragment(BugReportListFragment(),R.id.fragment_admin_panel)
        }
        binding.btnViewCourseRequest.setOnClickListener{
            replaceFragment(CourseRequestListFragment(),R.id.fragment_admin_panel)
        }
        return binding.root
    }

}