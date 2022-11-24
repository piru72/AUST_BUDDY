package com.example.homepage.groupTab.groupNotices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.FragmentGroupNoticesBinding


class GroupNoticesFragment(private var groupId: String ="") : Fragment() {
    private var _binding: FragmentGroupNoticesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentGroupNoticesBinding.inflate(inflater, container, false)
        binding.button.text = groupId
        return binding.root
    }

}