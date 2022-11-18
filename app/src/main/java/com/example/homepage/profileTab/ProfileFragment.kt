package com.example.homepage.profileTab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.example.homepage.R
import com.example.homepage.adminPanel.AdminPanelFragment
import com.example.homepage.adminPanel.bugReports.Model.BugReportsData
import com.example.homepage.databinding.FragmentProfileBinding
import com.example.homepage.loginSignup.SignInActivity
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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

        if (getCurrentUserId() == "TEQ09DxjCzfl913Wi7bZtER79iC3") {
            binding.btnAddTeacherCourse.visibility = View.VISIBLE
            binding.btnAddTeacherCourse.setOnClickListener {
                replaceFragment(AdminPanelFragment(), R.id.fragment_profile)
            }
        } else
            binding.btnAddTeacherCourse.visibility = View.INVISIBLE





        binding.btnReportBug.setOnClickListener {

            val rootLayout = layoutInflater.inflate(R.layout.bug_report_popup, null)

            val taskDescription = rootLayout.findViewById<EditText>(R.id.BugDescriptionPop)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
            val addButton = rootLayout.findViewById<Button>(R.id.sendButton)

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                binding.profileScreen, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {


                val reportDescription = taskDescription.text.toString()

                if (reportDescription == "")
                    makeToast("Please fill up all  the information")
                else {
                    setInformation(FirebaseAuth.getInstance().currentUser?.email.toString())
                    writeNewReport(getCurrentUserId(), getUserEmail(),reportDescription)
                    popupWindow.dismiss()
                }

            }
        }


        return binding.root
    }

    private fun writeNewReport(
        userId: String,
        reportersDetails: String,
        reportDescription: String
    ) {
        val database: DatabaseReference = Firebase.database.reference
        val key = database.child("posts").push().key
        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
        }

        val newReport =
            BugReportsData(userId, reportersDetails, reportDescription).toMap()
        val childReport = hashMapOf<String, Any>("/admin-bug-reports/$key" to newReport)
        database.updateChildren(childReport)
    }


}


