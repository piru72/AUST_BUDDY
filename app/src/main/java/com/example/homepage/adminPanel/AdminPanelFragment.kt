package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentAdminPanelBinding
import com.example.homepage.superClass.ReplaceFragment


class AdminPanelFragment : ReplaceFragment() {
    private var _binding: FragmentAdminPanelBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        _binding = FragmentAdminPanelBinding.inflate(inflater, container, false)
        binding.btnAddCoursesAdmin.setOnClickListener {
            val action = AdminPanelFragmentDirections.actionAdminPanelFragmentToAddCourseFragment("course-list", "view")
            findNavController().navigate(action)
        }
        binding.btnAddTeachersAdmin.setOnClickListener {
            val action = AdminPanelFragmentDirections.actionAdminPanelFragmentToAddTeachersFragment("teachers","view")
            findNavController().navigate(action)
        }
        binding.btnTeacherRequest.setOnClickListener {
            val action = AdminPanelFragmentDirections.actionAdminPanelFragmentToTeachersFragment("admin-teacher-request-list","view")
            findNavController().navigate(action)
        }
        binding.btnViewBugReports.setOnClickListener {
            val action = AdminPanelFragmentDirections.actionAdminPanelFragmentToBugReportListFragment()
            findNavController().navigate(action)
        }
        binding.btnViewCourseRequest.setOnClickListener {
        val action = AdminPanelFragmentDirections.actionAdminPanelFragmentToViewCourses2("admin-course-request-list","view")
            findNavController().navigate(action)
        }
        return binding.root
    }

}