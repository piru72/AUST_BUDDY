package com.example.homepage.adminPanel.courseRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.FragmentCourseRequestListBinding


class CourseRequestListFragment : Fragment() {
    private  lateinit var  _binding : FragmentCourseRequestListBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentCourseRequestListBinding.inflate(inflater,container,false)
        return binding.root
    }


}