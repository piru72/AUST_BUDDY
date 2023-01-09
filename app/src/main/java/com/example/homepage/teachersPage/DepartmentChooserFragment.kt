package com.example.homepage.teachersPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentDepartmentChooserBinding


class DepartmentChooserFragment : Fragment() {
    private var _binding: FragmentDepartmentChooserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentDepartmentChooserBinding.inflate(inflater, container, false)
        binding.btnCse.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/cse",
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btnEee.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/eee",
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btnTe.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/te",
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btnMe.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/me",
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btnIpe.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/ipe",
                    "view"
                )
            findNavController().navigate(action)
        }
        binding.btnAs.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/as",
                    "view"
                )
            findNavController().navigate(action)
        }
        return binding.root
    }

}