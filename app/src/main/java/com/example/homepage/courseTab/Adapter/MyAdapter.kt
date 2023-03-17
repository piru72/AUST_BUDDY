package com.example.homepage.courseTab.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.Model.CourseData
import com.example.homepage.R
import com.example.homepage.helperClass.Firebase.ChildUpdaterHelper
import com.example.homepage.helperClass.ItemViewHelper
import com.example.homepage.recyclerViewHolder.CourseViewHolder


class MyAdapter(private var userType: String) : RecyclerView.Adapter<CourseViewHolder>() {

    private val userList = ArrayList<CourseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_courses,
            parent, false
        )

        return CourseViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        val currentItem = userList[position]
        val context = holder.itemView.context

        holder.courseCode.text = currentItem.courseCode
        holder.courseName.text = currentItem.courseName

        val firebaseHelper = ChildUpdaterHelper()
        val itemViewHelper = ItemViewHelper(context)


        if (userType == "user") {
            holder.declineButton.visibility = View.GONE
            holder.approveButton.visibility = View.GONE
        } else {


            val parentNode = "admin-course-request-list"
            val childNode = currentItem.courseCode.toString()

            holder.declineButton.setOnClickListener {

                firebaseHelper.removeChild(parentNode, childNode)
                itemViewHelper.makeToast("Course request has been declined")

            }
            holder.approveButton.setOnClickListener {
                val fromPath = "$parentNode/$childNode"
                val toPath = "course-list/" + currentItem.coursePath.toString()

                firebaseHelper.moveChild(fromPath, toPath)

                firebaseHelper.removeChild(parentNode, childNode)
                itemViewHelper.makeToast("Course request has been approved")

            }

        }



        holder.exploreButton.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.driveLink))
            context.startActivity(i)
        }
        holder.shareButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, currentItem.driveLink)
            context.startActivity(Intent.createChooser(intent, "Share"))
        }


    }


    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList: List<CourseData>) {

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }


}