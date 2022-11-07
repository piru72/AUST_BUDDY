package com.example.homepage.courseTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.databinding.FragmentDepartmentSemesterChooserBinding
import com.example.homepage.superClass.ReplaceFragment


class DepartmentSemesterChooserFragment : ReplaceFragment() {
    private lateinit var _binding: FragmentDepartmentSemesterChooserBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentDepartmentSemesterChooserBinding.inflate(inflater, container, false)
        binding.btn31.setOnClickListener{
            replaceFragment(ViewCourses(),  R.id.fragment_department_semesterChooser)
        }
        return binding.root
    }
}