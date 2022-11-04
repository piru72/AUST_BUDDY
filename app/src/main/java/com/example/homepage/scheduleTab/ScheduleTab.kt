package com.example.homepage.scheduleTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentScheduleBinding
import com.example.homepage.scheduleTab.scheduleAdapter.ScheduleAdapter
import com.example.homepage.scheduleTab.scheduleModel.ScheduleViewModel

private lateinit var viewModel: ScheduleViewModel
private lateinit var recycler: RecyclerView
private var adapter: ScheduleAdapter? = null

class ScheduleTab : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.taskList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = ScheduleAdapter()
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[ScheduleViewModel::class.java]

        viewModel.allSchedules.observe(viewLifecycleOwner) {
            adapter!!.updateUserList(it)
        }

    }
}