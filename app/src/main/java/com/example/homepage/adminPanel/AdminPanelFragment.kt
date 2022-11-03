package com.example.homepage.adminPanel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.databinding.FragmentAddCourseBinding
import com.example.homepage.databinding.FragmentAdminPanelBinding
import com.example.homepage.profileTab.PrivacyFragment
import com.example.homepage.superClass.ReplaceFragment


class AdminPanelFragment : ReplaceFragment() {
 private var  _binding: FragmentAdminPanelBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        _binding = FragmentAdminPanelBinding.inflate(inflater, container, false)
        binding.btnAddCoursesAdmin.setOnClickListener{
            makeToast("Working")
            replaceFragment(AddCourseFragment(), R.id.fragment_admin_panel)
        }
        binding.btnAddTeachersAdmin.setOnClickListener{
            makeToast("Working")
        }
        return binding.root
    }

}