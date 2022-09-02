package com.example.homepage.courseTab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.courseTab.model.User

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val userList = ArrayList<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]

        holder.courseCode.text = currentItem.courseCode
        holder.courseName.text = currentItem.courseName

        holder.exploreButton.text = currentItem.driveLink

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList : List<User>){

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // The values for user_item variable are created here and assigned above
        val courseCode : TextView = itemView.findViewById(R.id.tvCourseCode)
        val courseName : TextView = itemView.findViewById(R.id.tvCourseName)
        val exploreButton : Button = itemView.findViewById(R.id.exploreButton)

    }



}