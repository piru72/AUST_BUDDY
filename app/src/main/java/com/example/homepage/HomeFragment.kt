package com.example.homepage

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.navigation.fragment.findNavController
import com.example.homepage.dataClass.UserAllData
import com.example.homepage.databinding.FragmentHomeBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import java.util.*


class HomeFragment : ReplaceFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Getting the users email
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()

        // Getting the users department and making a database reference with it
        setInformation(email)
        val selectedDepartment = getShortDepartment().uppercase(Locale.ROOT)
        val modifiedEmail = email.replace('.', '-')
        var userSemester = "Not given"
        val path = "/user-details/$modifiedEmail"
        val databaseReference = FirebaseDatabase.getInstance().getReference(path)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.getValue<UserAllData>()

                if (post != null) {
                    userSemester = post.userSemester.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

                makeToast("Error loading data")
            }
        }
        databaseReference.addValueEventListener(postListener)



        binding.btnPlaza.setOnClickListener {

            // Providing warning if semester not given
            if (userSemester == "Not given") {

                // POPUP to know the preference of the user
                val rootLayout = layoutInflater.inflate(R.layout.popup_update_semester, null)

                val laterButton = rootLayout.findViewById<Button>(R.id.btnUpdateLater)
                val nowButton = rootLayout.findViewById<Button>(R.id.btnUpdateNow)

                val popupWindow = PopupWindow(
                    rootLayout,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, true
                )

                popupWindow.update()
                popupWindow.elevation = 20.5F
                popupWindow.showAtLocation(

                    binding.fragmentHome, // Location to display popup window
                    Gravity.CENTER, // Exact position of layout to display popup
                    0, // X offset
                    -500// Y offset
                )

                laterButton.setOnClickListener {
                    val action =
                        HomeFragmentDirections.actionNavigationHomeToNavigationDepartmentSemesterChooser()
                    findNavController().navigate(action)
                    popupWindow.dismiss()
                }
                nowButton.setOnClickListener {
                    val action = HomeFragmentDirections.actionNavigationHomeToEditProfileFragment2()
                    findNavController().navigate(action)
                    popupWindow.dismiss()

                }
            } else {
                val action = HomeFragmentDirections.actionNavigationHomeToViewCourses2(
                    "$selectedDepartment/$userSemester",
                    "view"
                )
                findNavController().navigate(action)
            }
        }




        binding.btnBuses.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToBusFragment()
            findNavController().navigate(action)
        }
        binding.btnCgpa.setOnClickListener {

            if (getShortDepartment() != "cse") {
                makeToast("Under development")

            } else {
                val action = HomeFragmentDirections.actionNavigationHomeToCGPAFragment()
                findNavController().navigate(action)

            }

        }

        binding.btnRequest.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToRequestFragment()
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
            // TODO ADD TEACHERS OPTION TO HERE FOR OWN DEPARTMENT

//            val defaultDatabaseViewPath = "teachers-list/" + getShortDepartment()
//            // If users email contains a department the user will be automatically guided to his / her departments teacher list
//            val action = if (defaultDatabaseViewPath != getShortDepartment())
//                HomeFragmentDirections.actionNavigationHomeToTeachersFragment(
//                    defaultDatabaseViewPath,
//                    "sourceHome"
//                )
//            else
//                HomeFragmentDirections.actionNavigationHomeToDepartmentChooserFragment()
//
//            findNavController().navigate(action)
        }
        binding.btnRoutine.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToRoutineFragment()
            findNavController().navigate(action)
        }

        binding.btnShortCuts.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToShortCutFragment()
            findNavController().navigate(action)

        }


        return binding.root
    }


}
