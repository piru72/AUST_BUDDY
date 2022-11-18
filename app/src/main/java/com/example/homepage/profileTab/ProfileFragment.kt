package com.example.homepage.profileTab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.adminPanel.AdminPanelFragment
import com.example.homepage.databinding.FragmentProfileBinding
import com.example.homepage.loginSignup.SignInActivity
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : ReplaceFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.btnEditProfile.setOnClickListener {
            replaceFragment(EditProfileFragment(), R.id.fragment_profile)
        }
        binding.btnPrivacy.setOnClickListener {
            replaceFragment(PrivacyFragment(), R.id.fragment_profile)
        }
        binding.btnSettings.setOnClickListener {
            replaceFragment(SettingsFragment(), R.id.fragment_profile)
        }
        binding.btnInviteFriend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this app from\nhttps://github.com/piru72/Uni_buddy"
            )
            context?.startActivity(Intent.createChooser(intent, "Share"))
        }
        binding.btnAboutUs.setOnClickListener {
            replaceFragment(AboutDevFragment(), R.id.fragment_profile)
        }
        binding.btnLogOut.setOnClickListener {

            // TODO GOING FROM FRAGMENT TO ACTIVITY
            val i = Intent(activity, SignInActivity::class.java)
            startActivity(i)
            (activity as Activity?)!!.overridePendingTransition(0, 0)

            FirebaseAuth.getInstance().signOut()
        }

        val debugMode = true

        if (getCurrentUserId() == "TEQ09DxjCzfl913Wi7bZtER79iC3" || debugMode) {
            binding.btnAddTeacherCourse.visibility = View.VISIBLE
            binding.btnAddTeacherCourse.setOnClickListener {
                replaceFragment(AdminPanelFragment(), R.id.fragment_profile)
            }
        } else
            binding.btnAddTeacherCourse.visibility = View.INVISIBLE



        return binding.root
    }




}


