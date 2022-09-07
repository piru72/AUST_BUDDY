package com.example.homepage.teachersPage.TeacherAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.homepage.R
import com.example.homepage.teachersPage.TeacherModel.teacherData

@GlideModule
class teacherAdapter : RecyclerView.Adapter<teacherAdapter.MyViewHolder>() {

    private val userList = ArrayList<teacherData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.firstName.text = currentitem.name
        //holder.lastName.text = currentitem.lastName
        holder.age.text = currentitem.price
        Glide.with(holder.itemView.context).load(currentitem.img).into(holder.oree)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList : List<teacherData>){

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val firstName : TextView = itemView.findViewById(R.id.tvfirstName)
        //val lastName : TextView = itemView.findViewById(R.id.tvlastName)
        val age : TextView = itemView.findViewById(R.id.tvage)
        val oree : ImageView = itemView.findViewById(R.id.images)

    }

}