package com.example.homepage.profileTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.databinding.FragmentEditProfileBinding
import com.example.homepage.homeTab.DialogYearSemesterChooser
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


class EditProfileFragment : ReplaceFragment() {

    private lateinit var _binding: FragmentEditProfileBinding
    private val binding get() = _binding
    private val user = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)


        val email = user?.email.toString()
        setInformation(email)

        binding.IdReal.text = getUserId()
        binding.fullNameReal.text = getUserEmail()
        binding.departmentReal.text = getDepartment()
        binding.sessionReal.text = getSession()
        binding.userNameReal.text = getUserName()

        binding.btnUpdateSemester.setOnClickListener {
            val becomeAdminBottomSheetFragment = DialogYearSemesterChooser()
            becomeAdminBottomSheetFragment.show(parentFragmentManager, becomeAdminBottomSheetFragment.tag)
        }


        return binding.root
    }





}