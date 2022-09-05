package com.example.homepage

import android.R
import android.R.layout.activity_list_item
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentTodoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ScheduleFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!


    private lateinit var database: DatabaseReference
    private lateinit var taskReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var recycler: RecyclerView
    //private var adapter: TaskAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodoBinding.inflate(inflater, container, false)


       auth = Firebase.auth
       database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        taskReference = FirebaseDatabase.getInstance().getReference("user-tasks").child(user)
        recycler = binding.taskList

        recycler.layoutManager = LinearLayoutManager(context)

        binding.floatingActionButton.setOnClickListener {

            val showPopUp =PopUpFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager , "ShowPopup")
            //val taskName = showPopUp.view.id


            Toast.makeText(context, "this is toast message", Toast.LENGTH_SHORT).show()


        }

        return binding.root
    }

}