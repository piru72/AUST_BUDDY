package com.example.homepage.groupTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.databinding.FragmentCreateGroupBinding
import com.example.homepage.superClass.ReplaceFragment


class CreateGroupFragment : ReplaceFragment() {

    private var _binding: FragmentCreateGroupBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentCreateGroupBinding.inflate(inflater, container, false)
        return binding.root
    }


}