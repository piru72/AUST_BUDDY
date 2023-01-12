package com.example.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentHomeBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : ReplaceFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        binding.btnIums.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToWebView2(
                getString(R.string.universityStudentPortalLink),
                "view"
            )
            findNavController().navigate(action)
        }

        binding.btnTeachers.setOnClickListener {

            // Getting the users email
            val user = FirebaseAuth.getInstance().currentUser
            val email = user?.email.toString()

            // Getting the users department and making a database reference with it
            setInformation(email)
            val defaultDatabaseViewPath = "teachers-list/" + getShortDepartment()


            // If users email contains a department the user will be automatically guided to his / her departments teacher list
            val action = if (defaultDatabaseViewPath != getShortDepartment())
                HomeFragmentDirections.actionNavigationHomeToTeachersFragment(
                    defaultDatabaseViewPath,
                    "sourceHome"
                )
            else
                HomeFragmentDirections.actionNavigationHomeToDepartmentChooserFragment()

            findNavController().navigate(action)


        }
        binding.btnSyllabus.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToWebView2(
                getString(R.string.universitySyllabusLink),
                "view"
            )
            findNavController().navigate(action)
        }
        binding.btnCalender.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToWebView2(
                getString(R.string.universityAcademicCalenderLink),
                "view"
            )
            findNavController().navigate(action)
        }
        binding.btnBuses.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToBusFragment()
            findNavController().navigate(action)
        }
        binding.btnCgpa.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToCGPAFragment()
            findNavController().navigate(action)
        }

        binding.btnRequest.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToRequestFragment()
            findNavController().navigate(action)
        }
        binding.noticeButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToWebView2(
                getString(R.string.universityNoticeLink),
                "view"
            )
            findNavController().navigate(action)
        }

        binding.btnStoreDashboard.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToStoreDashboardFragment()
            findNavController().navigate(action)
        }

        binding.btnFavouriteWebPage.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToFavouriteWebPageFragment()
            findNavController().navigate(action)
        }
        binding.btnFavouriteTeachers.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToTeachersFragment(
                "user-favouriteTeachers",
                "view"
            )
            findNavController().navigate(action)
        }



        return binding.root
    }


}
