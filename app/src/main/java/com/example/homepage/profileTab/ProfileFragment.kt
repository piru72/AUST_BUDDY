package com.example.homepage.profileTab

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homepage.R
import com.example.homepage.SignInActivity
import com.example.homepage.adminPanel.AdminPanelFragment
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


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
        val btnBugReport = v.findViewById<Button>(R.id.btn_report_bug)
        val btnAddTeacherCourse = v.findViewById<Button>(R.id.btn_add_teacher_course)

        val layoutMain = v.findViewById<ConstraintLayout>(R.id.profile_screen)

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
//            replaceFragment(InviteFragment(), R.id.fragment_profile)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this app from\nhttps://github.com/piru72/Uni_buddy"
            )
            context?.startActivity(Intent.createChooser(intent, "Share"))
        }
        btnAboutUs.setOnClickListener {
            replaceFragment(AboutDevFragment(), R.id.fragment_profile)
        }
        btnLogOut.setOnClickListener {

            // TODO GOING FROM FRAGMENT TO ACTIVITY
            val i = Intent(activity, SignInActivity::class.java)
            startActivity(i)
            (activity as Activity?)!!.overridePendingTransition(0, 0)

            FirebaseAuth.getInstance().signOut()
        }


        btnAddTeacherCourse.setOnClickListener {

            replaceFragment(AdminPanelFragment(), R.id.fragment_profile)
//            val rootLayout = layoutInflater.inflate(R.layout.popup_password_for_add_teacher, null)
//
//            val passwordForAddTeacherCourse =
//                rootLayout.findViewById<EditText>(R.id.passwordForAddTeacherPop)
//            val cancelButton = rootLayout.findViewById<Button>(R.id.cancelButtonPop)
//            val confirmButton = rootLayout.findViewById<Button>(R.id.confirmButtonPop)
//
//            val popupWindow = PopupWindow(
//                rootLayout,
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT, true
//            )
//
//            popupWindow.update()
//            popupWindow.elevation = 20.5F
//            popupWindow.showAtLocation(
//
//                layoutMain, // Location to display popup window
//                Gravity.CENTER, // Exact position of layout to display popup
//                0, // X offset
//                -500// Y offset
//            )
//            cancelButton.setOnClickListener {
//                popupWindow.dismiss()
//            }
//            confirmButton.setOnClickListener{
//
//                if (passwordForAddTeacherCourse.text.toString() == "ThisIsAdmin")
//                {
//                    makeToast("Right password" + passwordForAddTeacherCourse.text.toString())
//                    popupWindow.dismiss()
//                    replaceFragment(AddCourseFragment(), R.id.fragment_profile)
//                }
//                else
//                    makeToast("Wrong password")
//
//
//            }

        }

        btnBugReport.setOnClickListener {

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

                layoutMain, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {


                val description = taskDescription.text.toString()

                if (description == "")
                    Toast.makeText(
                        context,
                        "Please fill up all  the information",
                        Toast.LENGTH_SHORT
                    ).show()
                else {

                    val email = "unibuddy890@gmail.com"
                    val addresses = email.split(",".toRegex()).toTypedArray()
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, addresses)
                        putExtra(Intent.EXTRA_SUBJECT, "FIX THE BUG OF UNIBUDDY")
                        putExtra(Intent.EXTRA_TEXT, description)
                    }
                    startActivity(intent)

                }
                popupWindow.dismiss()
            }
        }

        return v
    }


}


