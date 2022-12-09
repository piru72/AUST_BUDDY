package com.example.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentHomeBinding
import com.example.homepage.superClass.ReplaceFragment
import com.example.homepage.superClass.WebView
import com.example.homepage.teachersPage.TeachersFragment


class HomeFragment : ReplaceFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val currentState = R.id.fragment_home



        binding.btnIums.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universityStudentPortalLink)), currentState)
        }

        binding.btnTeachers.setOnClickListener {
            replaceFragment(TeachersFragment("teachers"), currentState)
        }
        binding.btnSyllabus.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universitySyllabusLink)), currentState)
        }
        binding.btnCalender.setOnClickListener {
            replaceFragment(
                WebView(getString(R.string.universityAcademicCalenderLink)),
                currentState
            )
        }
        binding.btnBuses.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToBusFragment()
            findNavController().navigate(action)
        }
        binding.btnCgpa.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToCGPAFragment()
            findNavController().navigate(action)
        }

        binding.btnRequest.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToRequestFragment()
            findNavController().navigate(action)
        }
        binding.noticeButton.setOnClickListener {
            replaceFragment(WebView(getString(R.string.universityNoticeLink)), currentState)
        }

        binding.btnStoreDashboard.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToStoreDashboardFragment()
            findNavController().navigate(action)
        }

        binding.btnFavouriteWebPage.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToFavouriteWebPageFragment()
            findNavController().navigate(action)
        }
        binding.btnFavouriteTeachers.setOnClickListener {
            replaceFragment(TeachersFragment("user-favouriteTeachers"), currentState)
        }



        return binding.root
    }


}
