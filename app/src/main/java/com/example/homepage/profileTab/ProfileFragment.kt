package com.example.homepage.profileTab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.homepage.R
import com.example.homepage.SignInActivity
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
            replaceFragment(InviteFragment(), R.id.fragment_profile)
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
        btnBugReport.setOnClickListener {
//            replaceFragment(BugReport(), R.id.fragment_profile)

            // TODO CREATE  A POP UP HERE TO RECEIVE THE EMAIL AND LATER ON SEND THE EMAIL TO THE CLIENT

//            binding.informativeText.text=""
            val rootLayout = layoutInflater.inflate(R.layout.custom_popup, null)

            val taskName = rootLayout.findViewById<EditText>(R.id.TaskNamePop)
            val taskDescription = rootLayout.findViewById<EditText>(R.id.TaskDescriptionPop)
            val taskDate = rootLayout.findViewById<EditText>(R.id.TaskDatePop)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)

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

                val name = taskName.text.toString()
                val description = taskDescription.text.toString()
                val date = taskDate.text.toString()

                if(name == "" || description=="")
                    Toast.makeText(context, "Please fill up all  the information", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Everything ok ", Toast.LENGTH_SHORT).show()
//                    writeNewTask(user, name, description, date)



                popupWindow.dismiss()
            }
        }

        return v
    }


}


