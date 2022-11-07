package com.example.homepage.courseTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.courseTab.Adapter.MyAdapter
import com.example.homepage.courseTab.Model.UserViewModel
import com.example.homepage.superClass.ReplaceFragment


private lateinit var userRecyclerView: RecyclerView
lateinit var adapter: MyAdapter

class ViewCourses(path: String) : ReplaceFragment() {
    private var semesterSelected= path

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        return inflater.inflate(R.layout.fragment_view_courses, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView = view.findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        adapter = MyAdapter()
        userRecyclerView.adapter = adapter

        makeToast(semesterSelected)
        var viewModel: UserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.allUsers.observe(viewLifecycleOwner) {

            adapter.updateUserList(it)

        }

    }
}