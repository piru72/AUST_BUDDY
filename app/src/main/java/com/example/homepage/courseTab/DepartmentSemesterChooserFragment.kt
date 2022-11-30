package com.example.homepage.courseTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.databinding.FragmentDepartmentSemesterChooserBinding
import com.example.homepage.superClass.ReplaceFragment


class DepartmentSemesterChooserFragment() : ReplaceFragment() {
    private lateinit var _binding: FragmentDepartmentSemesterChooserBinding
    private val binding get() = _binding

    private var selectedDepartment = "CSE/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentDepartmentSemesterChooserBinding.inflate(inflater, container, false)
        binding.btn11.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn11.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        binding.btn12.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn12.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        binding.btn21.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn21.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        binding.btn22.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn22.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        binding.btn31.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn31.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        binding.btn32.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn32.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        binding.btn41.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn41.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        binding.btn42.setOnClickListener {
            replaceFragment(
                ViewCourses(selectedDepartment + getDatabasePath(binding.btn42.text.toString())),
                R.id.fragment_department_semesterChooser
            )
        }
        return binding.root
    }
}