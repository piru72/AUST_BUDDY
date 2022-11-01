package com.example.homepage.teachersPage.TeacherAdapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
            R.layout.teachers_card,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        val context = holder.itemView.context

        holder.firstName.text = currentItem.name
        holder.designation.text = currentItem.designation
        Glide.with(context).load(currentItem.img).into(holder.tImage)
        holder.shareContactButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val teacherDetailsInfo = currentItem.name + "\n" +currentItem.designation + "\n" + currentItem.email + "\n" + currentItem.phone  + "\n\n"+ "@UniBuddy"
            intent.putExtra(Intent.EXTRA_TEXT,teacherDetailsInfo )
            context.startActivity(Intent.createChooser(intent, "Share"))
        }
        holder.emailTeacherButton.setOnClickListener {

            val email = currentItem.email
            val addresses = email?.split(",".toRegex())?.toTypedArray()
            val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            i.putExtra(Intent.EXTRA_EMAIL, addresses)
            context.startActivity(i)

        }
        holder.callTeacherButton.setOnClickListener {

            if (currentItem.phone.toString() == "Not Available")
                Toast.makeText(context, currentItem.phone.toString(), Toast.LENGTH_SHORT).show()
            else {
                val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currentItem.phone))
                context.startActivity(i)
            }
        }
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
        val designation : TextView = itemView.findViewById(R.id.tvDesignation)

        val tImage : ImageView = itemView.findViewById(R.id.images)
        val shareContactButton: Button = itemView.findViewById(R.id.btnShareContact)
        val emailTeacherButton: Button = itemView.findViewById(R.id.btnEmailTeacher)
        val callTeacherButton: Button = itemView.findViewById(R.id.btnCallTeacher)

    }

}