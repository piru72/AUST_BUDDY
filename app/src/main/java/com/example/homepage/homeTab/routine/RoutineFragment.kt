package com.example.homepage.homeTab.routine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.FragmentRoutineBinding


class RoutineFragment : Fragment() {
    private var _binding: FragmentRoutineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }


}