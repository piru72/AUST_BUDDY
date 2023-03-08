package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.homepage.databinding.FragmentAddTeachersBinding
import com.example.homepage.superClass.ReplaceFragment
import com.example.homepage.teachersPage.TeacherModel.TeacherData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddTeachersFragment : ReplaceFragment() {
    private lateinit var _binding: FragmentAddTeachersBinding
    private val viewBinding get() = _binding
    private lateinit var database: DatabaseReference

    private val arg: AddCourseFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTeachersBinding.inflate(inflater, container, false)

        setupButtons()

        return viewBinding.root
    }

    private fun setupButtons() {
        if (arg.reference == "admin-teacher-request-list")
            viewBinding.addTeachersButtonForm.text = "Request for adding teacher"

        viewBinding.addTeachersButtonForm.setOnClickListener {

            validateForm()

        }
    }

    private fun validateForm() {

        viewBinding.apply {
            val teachersName = teachersName.text.toString()
            val teachersDesignation = teachersDesignation.text.toString()
            val teachersContactNo = teachersContactNoText.text.toString()
            val teacherEmail = teachersEmailText.text.toString()
            val teachersImageLink = teachersImageLinkForm.text.toString()

            when {

                teachersName.contains(".") -> makeToast("Provide valid teachers name without . and only alphabets")
                teachersName == "" -> makeToast("Provide teachers name")
                teachersDesignation == "" -> makeToast("Provide teachers designation")
                teachersContactNo == "" -> makeToast("Provide teachers contact no or type Not Available")
                teacherEmail == "" -> makeToast(" Provide teachers email or type Not Available")
                teachersImageLink == "" -> makeToast("Provide teachers image link ")
                !validNumber(teachersContactNo) && teachersContactNo != "Not Available" -> makeToast(
                    "Provide a valid contact no"
                )
                !validEmail(teacherEmail) -> makeToast("Provide a valid email")
                !validWebsiteLink(teachersImageLink) -> makeToast("Provide valid image link")

                else -> {
                    writeNewTeacher(
                        teachersName,
                        teachersDesignation,
                        teachersContactNo,
                        teacherEmail, teachersImageLink
                    )
                    clearForm()
                    makeToast("Teachers data added successfully")

                }
            }


        }


    }

    private fun clearForm() {
        viewBinding.apply {
            teachersName.setText("")
            teachersDesignation.setText("")
            teachersContactNoText.setText("")
            teachersEmailText.setText("")
            teachersImageLinkForm.setText("")
        }
    }


    private fun writeNewTeacher(
        teachersName: String,
        teachersDesignation: String,
        teachersContactNo: String,
        teacherEmail: String,
        teachersImageLink: String
    ) {
        val newTeacher = TeacherData(
            teachersName,
            teachersImageLink,
            teachersDesignation,
            teachersContactNo,
            teacherEmail
        )
        val newPush = teacherEmail.replace(".", "-")
        val pushingPath = arg.reference
        database = Firebase.database.reference
        val teachersInformation = newTeacher.toMap()
        val childUpdate = hashMapOf<String, Any>(
            "/$pushingPath/$newPush" to teachersInformation
        )

        if (pushingPath == "admin-teacher-request-list") {
            val pushKey = teacherEmail.toString()
            val newPush = pushKey.replace(".", "-")

            val childUpdate = hashMapOf<String, Any>(
                "user-favouriteTeachers/${getCurrentUserId()}/$newPush" to teachersInformation
            )
            database.updateChildren(childUpdate)

        }

        database.updateChildren(childUpdate)

    }

}