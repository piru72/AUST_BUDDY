package com.example.homepage.adminPanel.adminRequest.Adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.adminPanel.adminRequest.Model.Admin
import com.example.homepage.databinding.CardAdminRequestBinding
import com.example.homepage.helperClass.Firebase.ChildUpdaterHelper


private val firebaseHelper = ChildUpdaterHelper()
class AdminRequestViewHolder(
    private val binding: CardAdminRequestBinding,
    private val adminRequestList: ArrayList<Admin>
) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.buttonDeclineAdmin.setOnClickListener(this)
        binding.buttonApproveAdmin.setOnClickListener(this)
    }

    fun bind(admin: Admin) {
        binding.textViewDepartmentAdmin.text = "Department: ${admin.department}"
        binding.textViewEmailAdmin.text = admin.email
        binding.textViewSemesterAdmin.text = "Semester: ${admin.semester}"
        binding.textViewYearAdmin.text = "Year: ${admin.year}"
    }


    override fun onClick(view: View) {
        val position = adapterPosition

        if (position != RecyclerView.NO_POSITION) {
            val currentItem = adminRequestList[position]
            val currentAdminRequest = currentItem.email?.replace(".", "-")
            when (view) {
                binding.buttonDeclineAdmin -> {
                    Toast.makeText(
                        view.context,
                        "${currentItem.email} Request is being removed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    firebaseHelper.removeChild(
                        "admin-admin-request",
                        currentAdminRequest.toString()
                    )
                }
                binding.buttonApproveAdmin -> {
                    val fromPath = "admin-admin-request/$currentAdminRequest"
                    val toPath = "admin-list/$currentAdminRequest"
                    firebaseHelper.moveChild(fromPath, toPath)
                    firebaseHelper.removeChild(
                        "admin-admin-request",
                        currentAdminRequest.toString()
                    )
                }
            }
        }
    }


}
