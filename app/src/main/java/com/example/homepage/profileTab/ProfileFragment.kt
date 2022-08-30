package com.example.homepage.profileTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.homepage.LogOutFragment
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment


class ProfileFragment : ReplaceFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnEditProfile = v.findViewById<Button>(R.id.btn_edit_profile)
        val btnPrivacy = v.findViewById<Button>(R.id.btn_privacy)
        val btnSettings = v.findViewById<Button>(R.id.btn_settings)
        val btnInviteFriend = v.findViewById<Button>(R.id.btn_invite_friend)
        val btnAboutUs = v.findViewById<Button>(R.id.btn_about_us)
        val btnLogOut = v.findViewById<Button>(R.id.btn_log_out)


        btnEditProfile.setOnClickListener {
            replaceFragment(EditProfileFragment(), R.id.fragment_profile)
        }
        btnPrivacy.setOnClickListener {
            replaceFragment(PrivacyFragment(), R.id.fragment_profile)
        }
        btnSettings.setOnClickListener {
            replaceFragment(SettingsFragment(), R.id.fragment_profile)
        }
        btnInviteFriend.setOnClickListener {
            replaceFragment(InviteFragment(), R.id.fragment_profile)
        }
        btnAboutUs.setOnClickListener {
            replaceFragment(AboutDevFragment(), R.id.fragment_profile)
        }
        btnLogOut.setOnClickListener {
            replaceFragment(LogOutFragment(), R.id.fragment_profile)
        }

        return v
    }


}


