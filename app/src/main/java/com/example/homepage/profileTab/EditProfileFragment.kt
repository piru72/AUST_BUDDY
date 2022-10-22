package com.example.homepage.profileTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


class EditProfileFragment : ReplaceFragment() {

    private val user = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        val email = user?.email.toString()

        setInformation(email)
        makeToast(getUserEmail())
        makeToast(getDepartment())
        makeToast(getUserName())
        makeToast(getUserId())
        return v
    }


}