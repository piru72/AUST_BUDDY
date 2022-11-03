package com.example.homepage.homeTab


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.teachersPage.TeacherAdapter.teacherAdapter
import com.example.homepage.teachersPage.TeacherModel.teacherViewModel

private lateinit var viewModel: teacherViewModel
private lateinit var userRecyclerView: RecyclerView
lateinit var adapter: teacherAdapter

class GradingsFragment : Fragment() {


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
       adapter = teacherAdapter()
       userRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[teacherViewModel::class.java]

        viewModel.allUsers.observe(viewLifecycleOwner) {

            adapter.updateUserList(it)

        }

    }




}