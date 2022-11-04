package com.example.homepage.courseTab.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.courseTab.Model.CourseData


class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val userList = ArrayList<CourseData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]

        holder.courseCode.text = currentItem.courseCode
        holder.courseName.text = currentItem.courseName
        //holder.exploreButton.text = currentItem.driveLink

        // TODO setup the link in here
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

    }


}