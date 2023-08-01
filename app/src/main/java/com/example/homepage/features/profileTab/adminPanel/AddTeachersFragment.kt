package com.example.homepage.features.profileTab.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.databinding.FragmentAddTeachersBinding
import com.example.homepage.utils.helpers.ReplaceFragment
import com.example.homepage.utils.helpers.ValidationHelper
import com.example.homepage.utils.models.TeacherData

class AddTeachersFragment : ReplaceFragment() {
    private lateinit var fragmentBinding: FragmentAddTeachersBinding
    private val viewBinding get() = fragmentBinding

    private val arg: AddCourseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentAddTeachersBinding.inflate(inflater, container, false)

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
            val teacher = extractForm()

            val validity = ValidationHelper()
            val childUpdater = ChildUpdaterHelper()
            val verdict = validity.validateTeacherForm(teacher)


            when {
                verdict != "Valid Data" -> makeToast(verdict)
                else -> {
                    childUpdater.writeNewTeacher(
                        teacher,
                        arg.reference,
                        getCurrentUserId()
                    )
                    clearForm()
                    makeToast("Teachers data added successfully")

                }
            }


        }


    }

    private fun extractForm(): TeacherData {
        val teacher: TeacherData
        viewBinding.apply {
            val name = teachersName.text.toString()
            val img = "https://w7.pngwing.com/pngs/184/113/png-transparent-user-profile-computer-icons-profile-heroes-black-silhouette-thumbnail.png"
            val phone = teachersContactNoText.text.toString()
            val designation = teachersDesignation.text.toString()
            val email = teachersEmailText.text.toString()
            teacher = TeacherData(name, img, phone, designation, email)
        }

        return teacher
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


}