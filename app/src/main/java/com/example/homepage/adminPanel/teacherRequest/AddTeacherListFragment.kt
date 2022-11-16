package com.example.homepage.adminPanel.teacherRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.FragmentAddTeacherListBinding


class AddTeacherListFragment : Fragment() {
   private  lateinit var _binding : FragmentAddTeacherListBinding
   private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentAddTeacherListBinding.inflate(inflater,container,false)

        return binding.root
    }


}