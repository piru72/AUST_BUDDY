package com.example.homepage.adminPanel.bugReports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.adminPanel.bugReports.Adapter.BugReportAdapter
import com.example.homepage.adminPanel.bugReports.Model.BugReportViewModel
import com.example.homepage.databinding.FragmentBugReportListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class BugReportListFragment : Fragment() {
    private lateinit var _binding: FragmentBugReportListBinding
    private val binding get() = _binding

    private lateinit var viewModel: BugReportViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: BugReportAdapter
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentBugReportListBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.bugReportList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = BugReportAdapter()
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[BugReportViewModel::class.java]

        viewModel.allBugReport.observe(viewLifecycleOwner){
            adapter!!.updateBugReportList(it)
        }

    }

}