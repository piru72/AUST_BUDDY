package com.example.homepage.homeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.R
import com.example.homepage.databinding.FragmentShortCutBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


class ShortCutFragment : ReplaceFragment() {
    private var fragmentBinding: FragmentShortCutBinding? = null
    private val viewBinding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentShortCutBinding.inflate(inflater, container, false)

        viewBinding.btnIums.setOnClickListener {
            val action = ShortCutFragmentDirections.actionShortCutFragmentToWebView2(
                getString(R.string.universityStudentPortalLink),
                "view"
            )
            findNavController().navigate(action)
        }


        viewBinding.btnCalender.setOnClickListener {
            val action = ShortCutFragmentDirections.actionShortCutFragmentToWebView2(
                getString(R.string.universityAcademicCalenderLink),
                "view"
            )
            findNavController().navigate(action)
        }

        viewBinding.noticeButton.setOnClickListener {
            val action = ShortCutFragmentDirections.actionShortCutFragmentToWebView2(
                getString(R.string.universityNoticeLink),
                "view"
            )
            findNavController().navigate(action)
        }


        viewBinding.btnFavouriteWebPage.setOnClickListener {
            val action = ShortCutFragmentDirections.actionShortCutFragmentToFavouriteWebPageFragment()
            findNavController().navigate(action)
        }

        // Getting the users email
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()

        // Getting the users department and making a database reference with it
        setInformation(email)

        viewBinding.btnCgpa.setOnClickListener {

            if (getShortDepartment() != "cse") {
                makeToast("Under development")

            } else {
                val action = ShortCutFragmentDirections.actionShortCutFragmentToCGPAFragment()
                findNavController().navigate(action)

            }

        }


        return viewBinding.root
    }


}