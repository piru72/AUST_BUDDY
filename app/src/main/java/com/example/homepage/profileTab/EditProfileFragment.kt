package com.example.homepage.profileTab

import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.example.homepage.R
import com.example.homepage.dataClass.UserAllData
import com.example.homepage.databinding.FragmentEditProfileBinding
import com.example.homepage.homeTab.DialogYearSemesterChooser
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class EditProfileFragment : ReplaceFragment() {

    private lateinit var _binding: FragmentEditProfileBinding
    private val binding get() = _binding
    private val user = FirebaseAuth.getInstance().currentUser
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val userV = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference


        val email = user?.email.toString()
        val modifiedEmail = email.replace('.', '-')
        setInformation(email)


        val path = "/user-details/$modifiedEmail"
        val databaseReference = FirebaseDatabase.getInstance().getReference(path)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.getValue<UserAllData>()
                if (post != null) {
                    binding.IdReal.text = post.userStudentId
                    binding.fullEmailReal.text = post.userEmail
                    binding.departmentReal.text = getDepartment()
                    binding.sessionReal.text = post.userSession
                    binding.userNameReal.text = post.userName
                    binding.semesterReal.text = post.userSemester
                    binding.sectionReal.text = post.userSection
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {

                makeToast("Error loading data")
            }
        }
        databaseReference.addValueEventListener(postListener)

        // HAVE TO IMPLEMENT coroutines HERE AS USING THIS FUNCTION WILL RETURN NULL VALUE BECAUSE THE DATA IS RETURNED BEFORE IT IS FETCHED
//        val databaseHelper = FirebaseRealtimeDatabase()
//        val databaseReference = FirebaseDatabase.getInstance().getReference("/user-details/$modifiedEmail")
//        val postData = databaseHelper.fetchData(databaseReference, UserAllData::class.java)
//
//        if (postData is UserAllData) {
//            postData.userSemester?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
//        }


        binding.btnUpdateSemester.setOnClickListener {
            val becomeAdminBottomSheetFragment = DialogYearSemesterChooser("UpdateSemester")
            becomeAdminBottomSheetFragment.show(
                parentFragmentManager,
                becomeAdminBottomSheetFragment.tag
            )
        }
        binding.btnUpdateSection.setOnClickListener {

            val rootLayout = layoutInflater.inflate(R.layout.popup_update_section, null)

            val cancelButton = rootLayout.findViewById<Button>(R.id.btnCancel)
            val updateButton = rootLayout.findViewById<Button>(R.id.btnUpdateNow)
            val section = rootLayout.findViewById<EditText>(R.id.editTextSection)
            section.text = binding.sectionReal.text as Editable?

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                binding.fragmentEditProfile, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            cancelButton .setOnClickListener {

                popupWindow.dismiss()
            }
            updateButton.setOnClickListener {
                val userName = binding.userNameReal.text.toString()
                val usersEmail = binding.fullEmailReal.text.toString()
                val studentId = binding.IdReal.text.toString()
                val session = binding.sessionReal.text.toString()
                val department = binding.departmentReal.text.toString()
                val semester = binding.semesterReal.text.toString()
                val sectionGiven = section.text.toString().uppercase()

                if ( sectionGiven == "A" || sectionGiven =="B" || sectionGiven == "C")
                {
                    updateSemester(userName, usersEmail, studentId, session, department, semester,sectionGiven)
                    popupWindow.dismiss()

                }
                else
                {
                    makeToast("Provide Valid semester!!")
                }




            }



        }


        return binding.root
    }

    private fun updateSemester(
        userName: String,
        userEmail: String,
        studentId: String,
        session: String,
        department: String,
        selectedSemester: String,
        selectedSection: String
    ) {

        val updatedUserInfo = UserAllData(
            userName,
            userEmail,
            studentId,
            session,
            department,
            selectedSemester,
            selectedSection
        )
        val newPush = userEmail.replace(".", "-")
        database = Firebase.database.reference
        val userInformation = updatedUserInfo.toMap()

        val childUpdate = hashMapOf<String, Any>(
            "/user-details/$newPush" to userInformation
        )

        database.updateChildren(childUpdate)

    }


}