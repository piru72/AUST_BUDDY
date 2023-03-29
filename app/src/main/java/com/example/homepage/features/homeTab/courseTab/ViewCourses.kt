package com.example.homepage.features.homeTab.courseTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.features.homeTab.courseTab.Adapter.MyAdapter
import com.example.homepage.features.homeTab.courseTab.Model.UserViewModel
import com.example.homepage.databinding.FragmentViewCoursesBinding
import com.example.homepage.utils.helpers.ReplaceFragment


class ViewCourses : ReplaceFragment() {

    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    private val args: ViewCoursesArgs by navArgs()
    private var fragmentBinding: FragmentViewCoursesBinding? = null
    private val viewBinding get() = fragmentBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        fragmentBinding = FragmentViewCoursesBinding.inflate(inflater, container, false)

        setupButtons()


        return viewBinding.root
    }

    private fun setupButtons() {
        if (args.view == "sourceSemesterChooser")
            viewBinding.btnSemesterChooser.visibility = View.GONE


        // Selecting the semester and navigating to all the semesters
        viewBinding.semesterReal.text = args.reference
        viewBinding.btnSemesterChooser.setOnClickListener {
            val action = ViewCoursesDirections.actionViewCourses2ToNavigationDepartmentSemesterChooser2()
            findNavController().navigate(action)

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView = view.findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)

        var semesterSelected = args.reference


        adapter = if (semesterSelected == "admin-course-request-list")
            MyAdapter("admin")
        else
            MyAdapter("user")
        userRecyclerView.adapter = adapter

        var viewModel: UserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        if (semesterSelected != "admin-course-request-list")
            semesterSelected = "course-list/$semesterSelected"
        viewModel.initialize(semesterSelected)
        viewModel.allUsers.observe(viewLifecycleOwner) {

            adapter.updateUserList(it)

        }

    }
}