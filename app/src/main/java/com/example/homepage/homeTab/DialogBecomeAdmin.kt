package com.example.homepage.homeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.homepage.R
import com.example.homepage.adminPanel.adminRequest.Model.Admin
import com.example.homepage.superClass.Helper
import com.example.homepage.superClass.spinner.SpinnerItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DialogBecomeAdmin : BottomSheetDialogFragment() {

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


        val yearSpinner = view.findViewById<Spinner>(R.id.spinner_year)
        val semesterSpinner = view.findViewById<Spinner>(R.id.spinner_semester)
        val requestButton = view.findViewById<Button>(R.id.btn_become_admin)

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



            requestForNewAdmin(department, selectedYear, selectedSemester, usersEmail)
        }

        return view
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