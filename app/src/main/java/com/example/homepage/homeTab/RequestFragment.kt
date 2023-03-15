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
import androidx.navigation.fragment.findNavController
import com.example.homepage.R
import com.example.homepage.Model.BugReportsData
import com.example.homepage.databinding.FragmentRequestBinding
import com.example.homepage.helperClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RequestFragment : ReplaceFragment() {
    private lateinit var fragmentBinding: FragmentRequestBinding
    private val viewBinding get() = fragmentBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        fragmentBinding = FragmentRequestBinding.inflate(inflater, container, false)
        database = Firebase.database.reference
        auth = Firebase.auth

        viewBinding.btnRequestForCourse.setOnClickListener {
            val action = RequestFragmentDirections.actionRequestFragmentToAddCourseFragment("admin-course-request-list","view")
            findNavController().navigate(action)
        }
        viewBinding.btnRequestForTeacher.setOnClickListener {
            val action = RequestFragmentDirections.actionRequestFragmentToAddTeachersFragment("admin-teacher-request-list","view")
            findNavController().navigate(action)
        }


        viewBinding.btnReportBug.setOnClickListener {

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

                viewBinding.requestScreen,
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
                    makeToast("Bug report sent to admins ")
                }

            }
        }
        viewBinding.btnBecomeAdmin.setOnClickListener {
            val becomeAdminBottomSheetFragment = DialogYearSemesterChooser("BecomeAdmin")
            becomeAdminBottomSheetFragment.show(parentFragmentManager, becomeAdminBottomSheetFragment.tag)
        }

        return viewBinding.root
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