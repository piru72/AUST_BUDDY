package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.FragmentAddTeachersBinding
import com.example.homepage.teachersPage.TeacherModel.TeacherData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddTeachersFragment : Fragment() {
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

        binding.addTeachersButtonForm.setOnClickListener{
            val teachersName = binding.teachersName.text.toString()
            val teachersDesignation = binding.teachersDesignation.text.toString()
            val teachersContactNo = binding.teachersContactNoText.text.toString()
            val teacherEmail = binding.teachersEmailText.text.toString()
            writeNewTeacher(teachersName,teachersDesignation,teachersContactNo,teacherEmail)
        }
        return binding.root
    }

    private fun writeNewTeacher(
        teachersName: String,
        teachersDesignation: String,
        teachersContactNo: String,
        teacherEmail: String
    ) {
        val newTeacher = TeacherData(teachersName, "image link",teachersContactNo,teachersDesignation,teacherEmail)
        val teachersInformation = newTeacher.toMap()
        val childUpdate = hashMapOf<String, Any>(
            "/teachers/$teachersName" to teachersInformation
        )
        database.updateChildren(childUpdate)

    }

}