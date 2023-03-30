package com.example.homepage.features.groupTab.Group.Adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.database.FirebaseUtils
import com.example.homepage.databinding.CardUserGroupsBinding
import com.example.homepage.features.groupTab.UserGroupsFragmentDirections
import com.example.homepage.utils.models.GroupData

class UserGroupViewHolder(private val binding: CardUserGroupsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(groupData: GroupData, inflater: LayoutInflater) {
        binding.userJoinedGroupName.text = groupData.groupName.toString()
        binding.userJoinedGroupsCreator.text = groupData.groupDetails.toString()
        binding.userJoinedGroupsCreatorName.text = groupData.universityId.toString()
        binding.userJoinedGroupId.text = groupData.groupId.toString()
        setupButtons(groupData, inflater)
    }


    private fun setupButtons(groupData: GroupData, inflater: LayoutInflater) {
        binding.root.setOnClickListener { v ->
            val action =
                UserGroupsFragmentDirections.actionNavigationUserGroupsToGroupNoticeFragment(
                    groupData.groupId.toString(),
                    "view"
                )
            val navController = Navigation.findNavController(v)
            navController.navigate(action)
        }

        binding.btnLeaveGroup.setOnClickListener {

            val rootLayout = inflater.inflate(R.layout.popup_leave_group, null)

            val cancelButton = rootLayout.findViewById<Button>(R.id.cancelButton)
            val leaveButton = rootLayout.findViewById<Button>(R.id.leaveButton)


            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                binding.btnLeaveGroup,
                Gravity.CENTER,
                0,
                -500
            )

            cancelButton.setOnClickListener {
                popupWindow.dismiss()
            }

            leaveButton.setOnClickListener {

                val user = FirebaseUtils.getUserId()
                val childUpdaterHelper = ChildUpdaterHelper()

                val parentNode = "/user-groups/$user"
                val childNode = groupData.groupId.toString()

                childUpdaterHelper.removeChild(parentNode, childNode)
                popupWindow.dismiss()
            }
        }
    }

}