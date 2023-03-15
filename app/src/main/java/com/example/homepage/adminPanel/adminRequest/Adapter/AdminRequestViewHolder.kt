package com.example.homepage.adminPanel.adminRequest.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.adminPanel.adminRequest.Model.Admin
import com.example.homepage.databinding.CardAdminRequestBinding
import com.example.homepage.helperClass.Firebase.ChildUpdaterHelper
import com.example.homepage.helperClass.ItemViewHelper


private val firebaseHelper = ChildUpdaterHelper()

class AdminRequestViewHolder(
    private val binding: CardAdminRequestBinding
) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(admin: Admin) {
        binding.textViewDepartmentAdmin.text = "Department: ${admin.department}"
        binding.textViewEmailAdmin.text = admin.email
        binding.textViewSemesterAdmin.text = "Semester: ${admin.semester}"
        binding.textViewYearAdmin.text = "Year: ${admin.year}"
        val itemViewHelper = ItemViewHelper(itemView.context)

        binding.buttonDeclineAdmin.setOnClickListener {

            itemViewHelper.makeToast("${admin.email} Request is being removed")
            firebaseHelper.removeAdminRequest(admin)
        }
        binding.buttonApproveAdmin.setOnClickListener {
            itemViewHelper.makeToast("${admin.email} Request is being approved.")
            firebaseHelper.approveAdminRequest(admin)
        }

    }


}
