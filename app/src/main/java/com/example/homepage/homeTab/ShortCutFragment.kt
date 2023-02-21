package com.example.homepage.homeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homepage.R
import com.example.homepage.databinding.FragmentShortCutBinding


class ShortCutFragment : Fragment() {
    private var _binding: FragmentShortCutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShortCutBinding.inflate(inflater, container, false)

        binding.btnIums.setOnClickListener {
            val action = ShortCutFragmentDirections.actionShortCutFragmentToWebView2(
                getString(R.string.universityStudentPortalLink),
                "view"
            )
            findNavController().navigate(action)
        }


        binding.btnCalender.setOnClickListener {
            val action = ShortCutFragmentDirections.actionShortCutFragmentToWebView2(
                getString(R.string.universityAcademicCalenderLink),
                "view"
            )
            findNavController().navigate(action)
        }

        binding.noticeButton.setOnClickListener {
            val action = ShortCutFragmentDirections.actionShortCutFragmentToWebView2(
                getString(R.string.universityNoticeLink),
                "view"
            )
            findNavController().navigate(action)
        }


        return binding.root
    }


}