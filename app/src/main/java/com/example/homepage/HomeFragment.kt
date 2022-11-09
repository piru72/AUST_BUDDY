package com.example.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.calculator.CGPAFragment
import com.example.homepage.databinding.FragmentHomeBinding
import com.example.homepage.homeTab.BusFragment
import com.example.homepage.homeTab.GradingsFragment
import com.example.homepage.homeTab.RequestFragment
import com.example.homepage.superClass.ReplaceFragment
import com.example.homepage.superClass.WebView


class HomeFragment : ReplaceFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val currentState = R.id.fragment_home

        // Mahy baby start working on this fragment
        // asdafsdafsaf
        //12313212332

        binding.btnIums.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universityStudentPortalLink)),currentState)
        }
        binding.btnAustOj.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universityOjLink)),currentState)
        }
        binding.btnTeachers.setOnClickListener {
            replaceFragment(GradingsFragment(), currentState)
        }
        binding.btnSyllabus.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universitySyllabusLink)),currentState)
        }
        binding.btnCalender.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universityAcademicCalenderLink)),currentState)
        }
        binding.btnBuses.setOnClickListener {
            replaceFragment(BusFragment(), currentState)
        }
        binding.btnCgpa.setOnClickListener {
            replaceFragment(CGPAFragment(), currentState)
        }
        binding.btnGradings.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universityGradingSystemLink)),currentState)
        }
        binding.btnRequest.setOnClickListener {
            replaceFragment(RequestFragment(), currentState)
        }
        binding.noticeButton.setOnClickListener{
            replaceFragment(WebView(getString(R.string.universityNoticeLink)),currentState)
        }

        return binding.root
    }


}