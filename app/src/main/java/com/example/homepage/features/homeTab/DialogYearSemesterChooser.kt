package com.example.homepage.features.homeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.homepage.R
import com.example.homepage.utils.models.Admin
import com.example.homepage.utils.models.UserAllData
import com.example.homepage.utils.helpers.Helper
import com.example.homepage.utils.helpers.spinner.SpinnerItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class DialogYearSemesterChooser(private val requestFor: String) : BottomSheetDialogFragment() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val userV = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_become_admin, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference
        val usersEmail = userV?.email.toString()
        val modifiedEmail = usersEmail.replace(".", "-")
        var userSectionDb = "Not given"
        val path = "/user-details/$modifiedEmail"
        val databaseReference = FirebaseDatabase.getInstance().getReference(path)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.getValue<UserAllData>()

                if (post != null) {
                    userSectionDb = post.userSection.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

                makeToast("Error loading data")
            }
        }
        databaseReference.addValueEventListener(postListener)


        val yearSpinner = view.findViewById<Spinner>(R.id.spinner_year)
        val semesterSpinner = view.findViewById<Spinner>(R.id.spinner_semester)
        val requestButton = view.findViewById<Button>(R.id.btn_become_admin)
        val title = view.findViewById<TextView>(R.id.textView_title_admin)


        if (requestFor == "UpdateSemester")
        {
            requestButton.text = "Update Semester"
            title.text = "Choose your semester"
        }

        val yearList = arrayOf(
            SpinnerItem("1", R.drawable.cate_official),
            SpinnerItem("2", R.drawable.cate_official),
            SpinnerItem("3", R.drawable.cate_official),
            SpinnerItem("4", R.drawable.cate_official),
            SpinnerItem("5", R.drawable.cate_official)
        )
        val helper = Helper()
        yearSpinner.adapter = helper.createSpinnerAdapter(requireContext(), yearList)

        val semesterList = arrayOf(
            SpinnerItem("1", R.drawable.cate_official),
            SpinnerItem("2", R.drawable.cate_official)
        )
        semesterSpinner.adapter = helper.createSpinnerAdapter(requireContext(), semesterList)

        requestButton.setOnClickListener {
            val selectedYear = yearSpinner.selectedItem.toString()
            val selectedSemester = semesterSpinner.selectedItem.toString()
            val usersEmail = userV?.email.toString()
            helper.setInformation(usersEmail)
            val department = helper.getShortDepartment()
            val userName = helper.getUserName()
            val studentId = helper.getUserId()
            val session = helper.getSession()





            if (requestFor == "UpdateSemester")
            {
                val semester = "year" + selectedYear + "semester"+ selectedSemester
                updateSemester(userName,usersEmail,studentId,session,department,semester,userSectionDb)
            }
            else
            {
                requestForNewAdmin(department, selectedYear, selectedSemester, usersEmail)
            }

            this.dismiss()
        }

        return view
    }

    private fun updateSemester(
        userName: String,
        userEmail: String,
        studentId: String,
        session: String,
        department: String,
        selectedSemester: String,
        userSectionDb: String
    ) {

        val updatedUserInfo = UserAllData (userName,userEmail,studentId,session,department,selectedSemester,userSectionDb)
        val newPush = userEmail.replace(".", "-")
        database = Firebase.database.reference
        val userInformation = updatedUserInfo.toMap()

        val childUpdate = hashMapOf<String, Any>(
            "/user-details/$newPush" to userInformation
        )

        database.updateChildren(childUpdate)

    }

    private fun requestForNewAdmin(
        department: String,
        selectedYear: String,
        selectedSemester: String,
        usersEmail: String
    ) {

        val newAdmin = Admin(
            department, selectedYear, selectedSemester, usersEmail
        )

        val newPush = usersEmail.replace(".", "-")
        database = Firebase.database.reference
        val adminsInformation = newAdmin.toMap()

        val childUpdate = hashMapOf<String, Any>(
            "/admin-admin-request/$newPush" to adminsInformation
        )

        database.updateChildren(childUpdate)

    }

    fun makeToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}