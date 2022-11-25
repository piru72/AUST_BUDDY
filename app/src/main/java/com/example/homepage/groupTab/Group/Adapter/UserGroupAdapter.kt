package com.example.homepage.groupTab.Group.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.groupTab.Group.Model.GroupData
import com.example.homepage.groupTab.groupNotices.GroupNoticesFragment

class UserGroupAdapter : RecyclerView.Adapter<UserGroupAdapter.UserGroupViewHolder>() {
    private val groups = ArrayList<GroupData>()

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
            val webFragment = GroupNoticesFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.userGroupFragment, webFragment).addToBackStack(
                    "tag"
                ).commit()
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

    }
}