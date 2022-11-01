package com.example.homepage.teachersPage.TeacherAdapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
            R.layout.teachers_card,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.firstName.text = currentitem.name
        holder.tdesignation.text = currentitem.designation
        holder.tEmail.text=currentitem.email
        holder.tPhone.text = currentitem.phone
        Glide.with(holder.itemView.context).load(currentitem.img).into(holder.tImage)
        holder.shareContactButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val teacherDetailsInfo = currentitem.name + "\n" +currentitem.designation + "\n" + currentitem.email + "\n" + currentitem.phone  + "\n\n"+ "N.B: Info taken from UniBuddy"
            intent.putExtra(Intent.EXTRA_TEXT,teacherDetailsInfo )
            context.startActivity(Intent.createChooser(intent, "Share"))
        }
        holder.emailTeacherButton.setOnClickListener {
            val context = holder.itemView.context
            val email = currentitem.email
            val addresses = email?.split(",".toRegex())?.toTypedArray()
            val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            i.putExtra(Intent.EXTRA_EMAIL, addresses)
            context.startActivity(i)

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
        //val lastName : TextView = itemView.findViewById(R.id.tvlastName)
        val tdesignation : TextView = itemView.findViewById(R.id.tvDesignation)

        val tImage : ImageView = itemView.findViewById(R.id.images)
        val tEmail : TextView = itemView.findViewById(R.id.tvEmail)
        val tPhone: TextView = itemView.findViewById(R.id.tvPhone)
        val shareContactButton: Button = itemView.findViewById(R.id.btnShareContact)
        val emailTeacherButton: Button = itemView.findViewById(R.id.btnEmailTeacher)
        val callTeacherButton: Button = itemView.findViewById(R.id.btnCallTeacher)

    }

}