package com.example.homepage.teachersPage


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
import com.example.homepage.databinding.FragmentTeachersBinding
import com.example.homepage.superClass.ReplaceFragment
import com.example.homepage.teachersPage.TeacherAdapter.teacherAdapter
import com.example.homepage.teachersPage.TeacherModel.teacherViewModel


class TeachersFragment : ReplaceFragment() {

    // Necessary objects and variables
    private val args: TeachersFragmentArgs by navArgs()
    private lateinit var viewModel: teacherViewModel
    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: teacherAdapter
    private var fragmentBinding: FragmentTeachersBinding? = null
    private val viewBinding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment and removing all previous views
        container?.removeAllViews()

        fragmentBinding = FragmentTeachersBinding.inflate(inflater, container, false)

        // If the user is navigating to this page from the home page only then this button will be visible
        if (args.viewPath == "sourceDepartmentChooser")
            viewBinding.btnOther.visibility = View.GONE

        viewBinding.btnOther.setOnClickListener {
            val action = TeachersFragmentDirections.actionTeachersFragmentToDepartmentChooserFragment()
            findNavController().navigate(action)
        }

        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The currently selected department
        val databaseViewPath = args.reference

        // Initializing recyclerview
        userRecyclerView = view.findViewById(R.id.teacher_list)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)

        // The type of user interacting with the page
        // 1. User - Basic users
        // 2. Admin - The super admin viewing this from the dashboard
        // 3. User-favourites - The user/ admin is looking at his favourite list

        var userType = "User"
        if (databaseViewPath == "admin-teacher-request-list")
            userType = "Admin"
        else if (databaseViewPath == "user-favouriteTeachers")
            userType = "User-favourites"

        // Initializing the adapter with the department and usertype
        adapter = teacherAdapter(userType,databaseViewPath)
        userRecyclerView.adapter = adapter

        // Initializing viewModel with appropriate database reference according to the databaseViewPath
        viewModel = ViewModelProvider(this)[teacherViewModel::class.java]
        if (databaseViewPath == "user-favouriteTeachers")
            viewModel.initialize(databaseViewPath + "/${getCurrentUserId()}")
        else
            viewModel.initialize(databaseViewPath)

        // updating the teacher list
        viewModel.allUsers.observe(viewLifecycleOwner) {
            adapter.updateUserList(it)
        }

    }


}