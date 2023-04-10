package com.example.homepage.utils.recyclerViewHolder

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.database.FirebaseUtils
import com.example.homepage.databinding.CardTeachersBinding
import com.example.homepage.utils.models.TeacherData
import com.google.firebase.database.FirebaseDatabase

class TeacherViewHolder(private val binding: CardTeachersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(currentItem: TeacherData, userType: String, databaseViewPath: String) {

        val context = binding.root.context

        binding.tvfirstName.text = currentItem.name
        binding.tvDesignation.text = currentItem.designation
        Glide.with(context).load(currentItem.img).into(binding.images)

        binding.btnShareContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val teacherDetailsInfo =
                currentItem.name + "\n" + currentItem.designation + "\n" + currentItem.email + "\n" + currentItem.phone + "\n\n" + "@AUST Buddy"
            intent.putExtra(Intent.EXTRA_TEXT, teacherDetailsInfo)
            context.startActivity(Intent.createChooser(intent, "Share"))
        }

        binding.btnEmailTeacher.setOnClickListener {

            val email = currentItem.email
            val addresses = email?.split(",".toRegex())?.toTypedArray()
            val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            i.putExtra(Intent.EXTRA_EMAIL, addresses)
            context.startActivity(i)

        }

        binding.btnCallTeacher.setOnClickListener {

            if (currentItem.phone.toString() == "Not Available")
                Toast.makeText(context, currentItem.phone.toString(), Toast.LENGTH_SHORT).show()
            else {
                val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currentItem.phone))
                context.startActivity(i)
            }
        }
        val firebaseHelper = ChildUpdaterHelper()
        val user = FirebaseUtils.getUserId()
        when (userType) {
            "Admin" -> binding.btnFavouriteContact.visibility = View.GONE
            "User-favourites" -> {

                binding.btnFavouriteContact.setOnClickListener {
                    Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show()


                    val parentNode = "user-favouriteTeachers/$user/"
                    val childNode = currentItem.email.toString().replace(".", "-")
                    firebaseHelper.removeChild(parentNode, childNode)
                }
            }
            else -> {
                binding.btnFavouriteContact.setOnClickListener {
                    Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show()


                    val newPush = currentItem.email.toString().replace(".", "-")

                    val fromPath = "$databaseViewPath/$newPush"
                    val toPath = "user-favouriteTeachers/$user/$newPush"
                    firebaseHelper.moveChild(fromPath, toPath)
                }

            }
        }

        // This options are for admins only
        val requestTeacherReference =
            FirebaseDatabase.getInstance().getReference("admin-teacher-request-list")
        if (userType == "Admin") {
            binding.adminControlLayout.visibility = View.VISIBLE
            binding.btnApproveTeacher.setOnClickListener {
                val teacherId = currentItem.email.toString().replace(".", "-")
                requestTeacherReference.child(teacherId).removeValue()
                Toast.makeText(context, "Teacher request has been approved", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.btnDeclineTeacher.setOnClickListener {
                val teacherId = currentItem.email.toString().replace(".", "-")
                requestTeacherReference.child(teacherId).removeValue()
                Toast.makeText(context, "Teacher request has been declined", Toast.LENGTH_SHORT)
                    .show()
            }
        } else
            binding.adminControlLayout.visibility = View.GONE
    }


}
