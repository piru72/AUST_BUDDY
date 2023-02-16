package com.example.homepage.homeTab.routine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.homepage.databinding.FragmentRoutineBinding
import com.example.homepage.homeTab.routine.model.Routine
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue


class RoutineFragment : ReplaceFragment() {
    private var _binding: FragmentRoutineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRoutineBinding.inflate(inflater, container, false)

        val path = "/routines/cse/year3/semester1/sectionC"
        var routineLink = "Not Found"
        val databaseReference = FirebaseDatabase.getInstance().getReference(path)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.getValue<Routine>()


                if (post != null) {
                    routineLink = post.image.toString()
                    Glide.with(requireContext()).load(routineLink).into(binding.images)
                }
                else
                {
                    makeToast(routineLink)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

                makeToast("Error loading data")
            }
        }
        databaseReference.addValueEventListener(postListener)
        return binding.root
    }


}