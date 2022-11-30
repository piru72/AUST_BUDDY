package com.example.homepage.teachersPage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment
import com.example.homepage.teachersPage.TeacherAdapter.teacherAdapter
import com.example.homepage.teachersPage.TeacherModel.teacherViewModel


class TeachersFragment(viewPath: String) : ReplaceFragment() {
    private val databaseViewPath = viewPath
    private lateinit var viewModel: teacherViewModel
    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: teacherAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        return inflater.inflate(R.layout.fragment_teachers, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView = view.findViewById(R.id.teacher_list)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        var userType = "User"
        if (databaseViewPath == "admin-teacher-request-list")
            userType = "Admin"
        else if (databaseViewPath == "user-favouriteTeachers")
            userType = "User-favourites"
        adapter = teacherAdapter(userType)
        userRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[teacherViewModel::class.java]
        if (databaseViewPath == "user-favouriteTeachers")
            viewModel.initialize(databaseViewPath + "/${getCurrentUserId()}")
        else
            viewModel.initialize(databaseViewPath)
        viewModel.allUsers.observe(viewLifecycleOwner) {

            adapter.updateUserList(it)

        }

    }


}