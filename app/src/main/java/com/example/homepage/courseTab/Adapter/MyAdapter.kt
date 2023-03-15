package com.example.homepage.courseTab.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.Model.CourseData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MyAdapter(private var userType: String) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val userList = ArrayList<CourseData>()
    private lateinit var database: DatabaseReference


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_courses,
            parent, false
        )
        database = Firebase.database.reference
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        val context = holder.itemView.context

        holder.courseCode.text = currentItem.courseCode
        holder.courseName.text = currentItem.courseName


        if (userType == "user") {
            holder.declineButton.visibility = View.GONE
            holder.approveButton.visibility = View.GONE
        } else {
            val requestCourseReference =
                FirebaseDatabase.getInstance().getReference("admin-course-request-list")
            holder.declineButton.setOnClickListener {
                currentItem.courseCode?.let { it1 ->
                    requestCourseReference.child(it1).removeValue()
                }
                Toast.makeText(context, "Course request has been declined", Toast.LENGTH_SHORT)
                    .show()
            }
            holder.approveButton.setOnClickListener {
                writeNewCourse(
                    currentItem.courseCode.toString(),
                    currentItem.courseName.toString(),
                    currentItem.driveLink.toString(),
                    currentItem.coursePath.toString()
                )
                currentItem.courseCode?.let { it1 ->
                    requestCourseReference.child(it1).removeValue()
                }

                Toast.makeText(context, "Course request has been approved", Toast.LENGTH_SHORT)
                    .show()

            }

        }



        holder.exploreButton.setOnClickListener {
            val context = holder.itemView.context
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.driveLink))
            context.startActivity(i)
        }
        holder.shareButton.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, currentItem.driveLink)
            context.startActivity(Intent.createChooser(intent, "Share"))
        }


    }

    private fun writeNewCourse(
        courseCode: String,
        courseName: String,
        courseDriveLink: String,
        coursePath: String,
    ) {

        val newCourse = CourseData(courseCode, courseName, courseDriveLink, coursePath)
        val courseDetails = newCourse.toMap()

        val childUpdateRequest = hashMapOf<String, Any>(
            "course-list/$coursePath" to courseDetails
        )
        database.updateChildren(childUpdateRequest)


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList: List<CourseData>) {

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // The values for user_item variable are created here and assigned above
        val courseCode: TextView = itemView.findViewById(R.id.tvCourseCode)
        val courseName: TextView = itemView.findViewById(R.id.tvCourseName)
        val exploreButton: Button = itemView.findViewById(R.id.exploreButton)
        val shareButton: Button = itemView.findViewById(R.id.shareButton)
        val approveButton: Button = itemView.findViewById(R.id.courseApproveButton)
        val declineButton: Button = itemView.findViewById(R.id.courseDeclineButton)

    }


}