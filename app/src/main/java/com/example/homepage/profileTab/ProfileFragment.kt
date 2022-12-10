package com.example.homepage.profileTab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
            val action = ProfileFragmentDirections.actionNavigationProfileToEditProfileFragment2()
            findNavController().navigate(action)
        }
        binding.btnPrivacy.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToPrivacyFragment()
            findNavController().navigate(action)
        }
        binding.btnSettings.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToSettingsFragment()
            findNavController().navigate(action)
        }
        binding.btnInviteFriend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Download this app for AUSTIANS\nhttps://play.google.com/store/apps/details?id=com.freondevs.homepage"
            )
            context?.startActivity(Intent.createChooser(intent, "Share"))
        }
        binding.btnAboutUs.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToAboutDevFragment()
            findNavController().navigate(action)
        }
        binding.btnLogOut.setOnClickListener {

            //  GOING FROM FRAGMENT TO ACTIVITY
            FirebaseAuth.getInstance().signOut()
            val i = Intent(activity, SignInActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
            (activity as Activity?)!!.overridePendingTransition(0, 0)

        }

        val debugMode = false

        if (getCurrentUserId() == "TEQ09DxjCzfl913Wi7bZtER79iC3" || debugMode) {
            binding.btnAddTeacherCourse.visibility = View.VISIBLE
            binding.btnAddTeacherCourse.setOnClickListener {
                val action = ProfileFragmentDirections.actionNavigationProfileToAdminPanelFragment()
                findNavController().navigate(action)
            }
        } else
            binding.btnAddTeacherCourse.visibility = View.INVISIBLE



        return binding.root
    }


}


