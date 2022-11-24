package com.example.homepage.groupTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.databinding.FragmentJoinGroupBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class JoinGroupFragment : ReplaceFragment() {

    private var _binding: FragmentJoinGroupBinding? = null
    private val binding get() = _binding!!
    private val user = FirebaseAuth.getInstance().currentUser
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentJoinGroupBinding.inflate(inflater, container, false)
        database = Firebase.database.reference
        val userId = getCurrentUserId()
        val email = user?.email.toString()
        binding.joinGroupButtonForm.setOnClickListener {
            val groupId = binding.groupId.text.toString()
            setInformation(email)
            if (groupId == "")
                makeToast("Provide group Id")
            else {
                addNewGroup(userId, getRollOmittedUserId(), groupId)
                binding.groupId.setText("")
            }

        }
        return binding.root
    }

    private fun addNewGroup(userId: Any, rollOmittedUserId: String, groupId: String) {
        val requestedGroupPath = "group-list/$rollOmittedUserId/$groupId"

        val pushingPath = "user-group/$userId/$groupId"
        val requestedChild =
            FirebaseDatabase.getInstance().getReference("group-list").child(rollOmittedUserId)
                .child(groupId).get()
    }


}