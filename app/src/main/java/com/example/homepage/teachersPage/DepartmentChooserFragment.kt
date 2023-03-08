package com.example.homepage.teachersPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentDepartmentChooserBinding


class DepartmentChooserFragment : Fragment() {
    private var fragmentBinding: FragmentDepartmentChooserBinding? = null
    private val viewBinding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        fragmentBinding = FragmentDepartmentChooserBinding.inflate(inflater, container, false)
        viewBinding.btnCse.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/cse",
                    "sourceDepartmentChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btnEee.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/eee",
                    "sourceDepartmentChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btnTe.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/te",
                    "sourceDepartmentChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btnMe.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/me",
                    "sourceDepartmentChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btnIpe.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/ipe",
                    "sourceDepartmentChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btnAs.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/as",
                    "sourceDepartmentChooser"
                )
            findNavController().navigate(action)
        }
        viewBinding.btnCe.setOnClickListener {
            val action =
                DepartmentChooserFragmentDirections.actionDepartmentChooserFragmentToTeachersFragment(
                    "teachers-list/ce",
                    "sourceDepartmentChooser"
                )
            findNavController().navigate(action)
        }
        return viewBinding.root
    }

}