package com.example.homepage.groupTab.Group.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.groupNoticePage.GroupNoticeFragment
import com.example.homepage.groupTab.Group.Model.GroupData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserGroupAdapter : RecyclerView.Adapter<UserGroupAdapter.UserGroupViewHolder>() {
    private val groups = ArrayList<GroupData>()
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGroupViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_user_groups,
            parent, false
        )
        return UserGroupViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserGroupViewHolder, position: Int) {
        val currentGroup = groups[position]
        holder.groupName.text = currentGroup.groupName.toString()
        holder.groupDetails.text = currentGroup.groupDetails.toString()
        holder.groupCreatorName.text = currentGroup.universityId.toString()
        holder.groupId.text = currentGroup.groupId.toString()

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val webFragment = GroupNoticeFragment(currentGroup.groupId.toString())
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.userGroupFragment, webFragment).addToBackStack(
                    "tag"
                ).commit()
        }
        holder.leaveGroup.setOnClickListener {
            auth = Firebase.auth
            database = Firebase.database.reference
            val user = auth.currentUser!!.uid
            val groupReference = FirebaseDatabase.getInstance().getReference("user-groups").child(user)
            groupReference.child(currentGroup.groupId.toString()).removeValue()
        }
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    fun updateUserGroupList(groupData: List<GroupData>) {

        this.groups.clear()
        this.groups.addAll(groupData)
        notifyDataSetChanged()
    }


    class UserGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupName: TextView = itemView.findViewById(R.id.userJoinedGroupName)
        val groupDetails: TextView = itemView.findViewById(R.id.userJoinedGroupsCreator)
        val groupCreatorName: TextView = itemView.findViewById(R.id.userJoinedGroupsCreatorName)
        val groupId: TextView = itemView.findViewById(R.id.userJoinedGroupId)
        val leaveGroup : Button = itemView.findViewById(R.id.btnLeaveGroup)

    }
}