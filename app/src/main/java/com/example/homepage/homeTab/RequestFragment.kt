package com.example.homepage.homeTab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.databinding.FragmentRequestBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RequestFragment : ReplaceFragment() {
    private lateinit var _binding: FragmentRequestBinding
    private val binding get() = _binding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        database = Firebase.database.reference
        auth = Firebase.auth
        val user = auth.currentUser!!.uid
        binding.sendBtn.setOnClickListener {

            val message = binding.message.text.toString()
            if (message != "")
                writeNewRequest(user , message)
            else
                makeToast("Fill up all the fields")
        }

        return binding.root
    }

    private fun writeNewRequest(userId :String ,message: String) {

        val key = database.child("posts").push().key
        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        val requestMessage = mapOf("message" to message, "userId" to userId)
        val childUpdates = hashMapOf<String, Any>(
            "/material-request-list/$key" to requestMessage
        )
        database.updateChildren(childUpdates)

    }

}