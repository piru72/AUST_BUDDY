package com.example.homepage

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentTodoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
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
        //val user = auth.currentUser!!.uid
        //taskReference = FirebaseDatabase.getInstance().getReference("user-tasks").child(user)
        recycler = binding.taskList

        return binding.root
    }

}