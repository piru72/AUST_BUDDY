package com.example.homepage.teachersPage.TeacherAdapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment
import com.example.homepage.teachersPage.TeacherModel.TeacherData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@GlideModule
class teacherAdapter(private val userType: String) :
    RecyclerView.Adapter<teacherAdapter.MyViewHolder>() {

    private val userList = ArrayList<TeacherData>()
    private lateinit var database: DatabaseReference
    private val supReplace = ReplaceFragment()
    private val user = FirebaseAuth.getInstance().currentUser?.uid
    private var selectedIds: List<Any> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        database = Firebase.database.reference
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_teachers,
            parent, false
        )
        return MyViewHolder(itemView)

    }
    fun setSelectedIds(selectedIds: List<Any>) {
        this.selectedIds = selectedIds
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        val context = holder.itemView.context
        holder.designation.text = currentItem.designation
        Glide.with(context).load(currentItem.img).into(holder.tImage)
        holder.shareContactButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val teacherDetailsInfo =
                currentItem.name + "\n" + currentItem.designation + "\n" + currentItem.email + "\n" + currentItem.phone + "\n\n" + "@UniBuddy"
            intent.putExtra(Intent.EXTRA_TEXT, teacherDetailsInfo)
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


        holder.addToFavouriteButton.setOnClickListener {
            val fromPath =
                FirebaseDatabase.getInstance().getReference("teachers/${currentItem.name}")

            val pushKey = currentItem.email.toString()
            val newPush = pushKey.replace(".", "")

            val toPath =
                FirebaseDatabase.getInstance().getReference("user-favouriteTeachers/$user/$newPush")

            moveTeacherDetails(fromPath, toPath)
        }

        // This options are for admins only
        val requestTeacherReference =
            FirebaseDatabase.getInstance().getReference("admin-teacher-request-list")
        if (userType == "Admin") {
            holder.adminControlLayout.visibility = View.VISIBLE
            holder.approveTeacherButton.setOnClickListener {


                currentItem.name?.let { it1 ->
                    currentItem.designation?.let { it2 ->
                        currentItem.phone?.let { it3 ->
                            currentItem.email?.let { it4 ->
                                currentItem.img?.let { it5 ->
                                    approveNewTeacher(
                                        it1,
                                        it2, it3, it4, it5
                                    )
                                }
                            }
                        }
                    }
                }
                currentItem.name?.let { it1 -> requestTeacherReference.child(it1).removeValue() }
                Toast.makeText(context, "Teacher request has been approved", Toast.LENGTH_SHORT)
                    .show()
            }
            holder.declineTeacherButton.setOnClickListener {
                currentItem.name?.let { it1 -> requestTeacherReference.child(it1).removeValue() }
                Toast.makeText(context, "Teacher request has been declined", Toast.LENGTH_SHORT)
                    .show()
            }
        } else
            holder.adminControlLayout.visibility = View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList: List<TeacherData>) {

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    private fun approveNewTeacher(
        teachersName: String,
        teachersDesignation: String,
        teachersContactNo: String,
        teacherEmail: String,
        teachersImageLink: String
    ) {
        val newTeacher = TeacherData(
            teachersName,
            teachersImageLink,
            teachersDesignation,
            teachersContactNo,
            teacherEmail
        )
        val teachersInformation = newTeacher.toMap()
        val childUpdate = hashMapOf<String, Any>(
            "/teachers/$teachersName" to teachersInformation
        )
        database.updateChildren(childUpdate)

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val firstName: TextView = itemView.findViewById(R.id.tvfirstName)
        val designation: TextView = itemView.findViewById(R.id.tvDesignation)

        val tImage: ImageView = itemView.findViewById(R.id.images)
        val shareContactButton: Button = itemView.findViewById(R.id.btnShareContact)
        val emailTeacherButton: Button = itemView.findViewById(R.id.btnEmailTeacher)
        val callTeacherButton: Button = itemView.findViewById(R.id.btnCallTeacher)
        val addToFavouriteButton: Button = itemView.findViewById(R.id.btnFavouriteContact)

        val adminControlLayout: LinearLayout = itemView.findViewById(R.id.adminControlLayout)
        val approveTeacherButton: Button = itemView.findViewById(R.id.btnApproveTeacher)
        val declineTeacherButton: Button = itemView.findViewById(R.id.btnDeclineTeacher)

    }

    private fun moveTeacherDetails(fromPath: DatabaseReference, toPath: DatabaseReference) {
        fromPath.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                toPath.setValue(
                    dataSnapshot.value
                )
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}