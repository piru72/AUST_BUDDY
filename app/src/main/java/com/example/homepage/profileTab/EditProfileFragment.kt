package com.example.homepage.profileTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.dataClass.UserAllData
import com.example.homepage.databinding.FragmentEditProfileBinding
import com.example.homepage.homeTab.DialogYearSemesterChooser
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue


class EditProfileFragment : ReplaceFragment() {

    private lateinit var _binding: FragmentEditProfileBinding
    private val binding get() = _binding
    private val user = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)


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
                    binding.fullNameReal.text = post.userEmail
                    binding.departmentReal.text = getDepartment()
                    binding.sessionReal.text = post.userSession
                    binding.userNameReal.text = post.userName
                    binding.semesterReal.text = post.userSemester
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


        return binding.root
    }


}