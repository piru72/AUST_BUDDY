package com.example.homepage.adminPanel.adminRequest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.adminPanel.adminRequest.Model.Admin
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AdminReqeustAdapter : RecyclerView.Adapter<AdminReqeustAdapter.AdminRequestViewHolder>(){


    private val adminRequestList= ArrayList<Admin>()
    private lateinit var database: DatabaseReference




    fun updateAdminRequestList(adminRequestList: List<Admin>) {

        this.adminRequestList.clear()
        this.adminRequestList.addAll(adminRequestList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminRequestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_admin_request,
            parent, false
        )
        database = Firebase.database.reference

        return AdminRequestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdminRequestViewHolder, position: Int) {
        val currentItem = adminRequestList[position]
        val context = holder.itemView.context
        holder.departmentAdmin.text = "Department : " +currentItem.department
        holder.emailAdmin.text = currentItem.email
        holder.yearAdmin.text = "Year : " +currentItem.year
        holder.semesterAdmin.text = "Semester : " +currentItem.semester
    }

    override fun getItemCount(): Int {
        return adminRequestList.size
    }


    class AdminRequestViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        // The values for user_item variable are created here and assigned above
        val departmentAdmin: TextView = itemView.findViewById(R.id.textView_Department_admin)
        val emailAdmin : TextView  = itemView.findViewById(R.id.textView_email_admin)
        val semesterAdmin : TextView  = itemView.findViewById(R.id.textView_semester_admin)
        val yearAdmin : TextView  = itemView.findViewById(R.id.textView_year_admin)

        val declineAdmin : Button  = itemView.findViewById(R.id.button_decline_admin)
        val approveAdmin : Button  = itemView.findViewById(R.id.button_approve_admin)


    }
}