package com.example.homepage.groupTab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentCreateGroupBinding
import com.example.homepage.groupTab.Group.Model.GroupData
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CreateGroupFragment : ReplaceFragment() {

    private var fragmentBinding: FragmentCreateGroupBinding? = null
    private val viewBinding get() = fragmentBinding!!
    private lateinit var auth: FirebaseAuth
    private val user = FirebaseAuth.getInstance().currentUser
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        fragmentBinding = FragmentCreateGroupBinding.inflate(inflater, container, false)

        database = Firebase.database.reference


        viewBinding.createGroupButtonForm.setOnClickListener {

            val groupName = viewBinding.groupName.text.toString()
            val groupDetails = viewBinding.groupDetails.text.toString()
            val email = user?.email.toString()
            setInformation(email)
            if (groupName == "")
                makeToast("Provide Group Name ")
            else if (groupDetails == "")
                makeToast("Provide Group Details ")
            else {
                createNewGroup(email, getUserId(), getRollOmittedUserId(), groupName, groupDetails)
                viewBinding.groupName.setText("")
                viewBinding.groupDetails.setText("")
                val action = CreateGroupFragmentDirections.actionCreateGroupFragmentToNavigationUserGroups()
                findNavController().navigate(action)
            }

        }


        return viewBinding.root
    }

    private fun createNewGroup(
        userEmail: String,
        universityId: String,
        rollOmittedUserId: String,
        groupName: String,
        groupDetails: String
    ) {
        var key = database.child("posts").push().key


        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        key = key.dropLast(key.length - 7)
        val pushingPath = "group-list/$rollOmittedUserId/$key"

        val newGroup = GroupData(userEmail, universityId, groupName, groupDetails, pushingPath, key)
        val groupDetailsChild = newGroup.toMap()
        val groupChild = hashMapOf<String, Any>(
            "$pushingPath" to groupDetailsChild,
            "user-groups/${getCurrentUserId()}/$key" to groupDetailsChild
        )
        database.updateChildren(groupChild)
    }


}