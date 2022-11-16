package com.example.homepage.adminPanel.bugReports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.FragmentBugReportListBinding


class BugReportListFragment : Fragment() {
   private lateinit var  _binding : FragmentBugReportListBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentBugReportListBinding.inflate(inflater, container, false)

        return binding.root
    }

}