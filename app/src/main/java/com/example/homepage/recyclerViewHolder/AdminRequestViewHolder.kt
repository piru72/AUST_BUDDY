package com.example.homepage.recyclerViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.Model.Admin
import com.example.homepage.databinding.CardAdminRequestBinding
import com.example.homepage.helperClass.Firebase.ChildUpdaterHelper
import com.example.homepage.helperClass.ItemViewHelper




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
        val firebaseHelper = ChildUpdaterHelper()

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
