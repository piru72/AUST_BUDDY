package com.example.homepage.homeTab

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
import com.example.homepage.adminPanel.AddCourseFragment
import com.example.homepage.adminPanel.AddTeachersFragment
import com.example.homepage.adminPanel.bugReports.Model.BugReportsData
import com.example.homepage.databinding.FragmentRequestBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RequestFragment : ReplaceFragment() {
    private lateinit var _binding: FragmentRequestBinding
    private val binding get() = _binding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        database = Firebase.database.reference
        auth = Firebase.auth
        val user = auth.currentUser!!.uid
        binding.btnRequestForCourse.setOnClickListener {
            replaceFragment(AddCourseFragment("admin-course-request-list"), R.id.requestScreen)
        }
        binding.btnRequestForTeacher.setOnClickListener {
            replaceFragment(AddTeachersFragment("admin-teacher-request-list"), R.id.requestScreen)
        }


        binding.btnReportBug.setOnClickListener {

            val rootLayout = layoutInflater.inflate(R.layout.popup_bug_report, null)

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

                binding.requestScreen,
                Gravity.CENTER,
                0,
                -500
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
                    writeNewReport(getCurrentUserId(), getUserEmail(), reportDescription)
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
            BugReportsData(userId, reportersDetails, reportDescription, key).toMap()
        val childReport = hashMapOf<String, Any>("/admin-bug-reports/$key" to newReport)
        database.updateChildren(childReport)
    }

}