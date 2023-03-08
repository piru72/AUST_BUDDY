package com.example.homepage.courseTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentDepartmentSemesterChooserBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class DepartmentSemesterChooserFragment() : ReplaceFragment() {
    private lateinit var fragmentBinding: FragmentDepartmentSemesterChooserBinding
    private val viewBinding get() = fragmentBinding

    private var selectedDepartment = "CSE/"
    private val userV = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        fragmentBinding = FragmentDepartmentSemesterChooserBinding.inflate(inflater, container, false)
        val email = userV?.email.toString()
        setInformation(email)
        selectedDepartment = getShortDepartment().uppercase(Locale.ROOT)
        selectedDepartment +="/"
        viewBinding.btn11.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn11.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btn12.setOnClickListener {
            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn12.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btn21.setOnClickListener {
            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn21.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btn22.setOnClickListener {
            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn22.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btn31.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn31.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btn32.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn32.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btn41.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn41.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btn42.setOnClickListener {

            val action =
                DepartmentSemesterChooserFragmentDirections.actionNavigationDepartmentSemesterChooserToViewCourses2(
                    selectedDepartment + getDatabasePath(viewBinding.btn42.text.toString()),
                    "sourceSemesterChooser"
                )
            findNavController().navigate(action)
        }
        return viewBinding.root
    }
}