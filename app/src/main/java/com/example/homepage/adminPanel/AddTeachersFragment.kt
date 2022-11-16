package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.databinding.FragmentAddTeachersBinding
import com.example.homepage.superClass.ReplaceFragment
import com.example.homepage.teachersPage.TeacherModel.TeacherData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddTeachersFragment : ReplaceFragment() {
    private lateinit var _binding: FragmentAddTeachersBinding
    private val binding get() = _binding
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentAddTeachersBinding.inflate(inflater, container, false)
        database = Firebase.database.reference

        binding.addTeachersButtonForm.setOnClickListener {
            val teachersName = binding.teachersName.text.toString()
            val teachersDesignation = binding.teachersDesignation.text.toString()
            val teachersContactNo = binding.teachersContactNoText.text.toString()
            val teacherEmail = binding.teachersEmailText.text.toString()
            val teachersImageLink = binding.teachersImageLinkForm.text.toString()

            if (teachersName == "")
                makeToast("Provide teachers name")
            else if (teachersDesignation == "")
                makeToast("Provide teachers designation")
            else if (teachersContactNo == "")
                makeToast("Provide teachers contact no or type Not Available")
            else if (teacherEmail == "")
                makeToast(" Provide teachers email or type Not Available")
            else if (teachersImageLink == "")
                makeToast("Provide teachers image link ")
            else {
                if (!validNumber(teachersContactNo)&& teachersContactNo != "Not Available")
                    makeToast("Provide a valid contact no")
                else if (!validEmail(teacherEmail))
                    makeToast("Provide a valid email")
                else if (!validWebsiteLink(teachersImageLink))
                    makeToast("Provide valid image link")
                else {
                    writeNewTeacher(
                        teachersName,
                        teachersDesignation,
                        teachersContactNo,
                        teacherEmail,teachersImageLink
                    )
                    binding.teachersName.setText("")
                    binding.teachersDesignation.setText("")
                    binding.teachersContactNoText.setText("")
                    binding.teachersEmailText.setText("")
                    binding.teachersImageLinkForm.setText("")
                    makeToast("Teachers data added successfully")
                }

            }


        }
        return binding.root
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
        val teachersInformation = newTeacher.toMap()
        val childUpdate = hashMapOf<String, Any>(
            "/teachers/$teachersName" to teachersInformation
        )
        database.updateChildren(childUpdate)

    }

}