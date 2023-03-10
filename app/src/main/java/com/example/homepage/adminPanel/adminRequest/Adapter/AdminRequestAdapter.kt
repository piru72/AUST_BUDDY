package com.example.homepage.adminPanel.adminRequest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.adminPanel.adminRequest.Model.Admin
import com.example.homepage.databinding.CardAdminRequestBinding
import com.example.homepage.helperClass.Firebase.ChildUpdaterHelper

class AdminRequestAdapter : RecyclerView.Adapter<AdminRequestAdapter.AdminRequestViewHolder>() {


    private val adminRequestList = ArrayList<Admin>()
    private val firebaseHelper = ChildUpdaterHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminRequestViewHolder {
        val binding =
            CardAdminRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AdminRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminRequestViewHolder, position: Int) {
        holder.bind(adminRequestList[position])
    }

    override fun getItemCount(): Int {
        return adminRequestList.size
    }


    fun updateAdminRequestList(adminRequestList: List<Admin>) {

        this.adminRequestList.clear()
        this.adminRequestList.addAll(adminRequestList)
        notifyDataSetChanged()

    }

    inner class AdminRequestViewHolder(private val binding: CardAdminRequestBinding) :
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
}