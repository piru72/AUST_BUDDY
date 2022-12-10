package com.example.homepage.courseTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn11.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btn12.setOnClickListener {
            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn12.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btn21.setOnClickListener {
            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn21.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btn22.setOnClickListener {
            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn22.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btn31.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn31.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btn32.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn32.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btn41.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn41.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btn42.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(binding.btn42.text.toString()),
                    "view"
                )
            findNavController().navigate(action)
        }
        return binding.root
    }
}