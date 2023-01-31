package com.example.homepage.courseTab

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
import com.example.homepage.courseTab.Adapter.MyAdapter
import com.example.homepage.courseTab.Model.UserViewModel
import com.example.homepage.databinding.FragmentViewCoursesBinding
import com.example.homepage.superClass.ReplaceFragment


class ViewCourses : ReplaceFragment() {

    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    private val args: ViewCoursesArgs by navArgs()
    private var _binding: FragmentViewCoursesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        _binding = FragmentViewCoursesBinding.inflate(inflater, container, false)


        // Selecting the semester and navigating to all the semesters
        binding.semesterReal.text = args.reference
        binding.btnSemesterChooser.setOnClickListener {
            val action = ViewCoursesDirections.actionViewCourses2ToNavigationDepartmentSemesterChooser2()
            findNavController().navigate(action)

        }
        return binding.root
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