package com.example.homepage.features.homeTab.routine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.homepage.utils.models.UserAllData
import com.example.homepage.databinding.FragmentRoutineBinding
import com.example.homepage.utils.models.Routine
import com.example.homepage.utils.helpers.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue


class RoutineFragment : ReplaceFragment() {
    private var fragmentBinding: FragmentRoutineBinding? = null
    private val viewBinding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentRoutineBinding.inflate(inflater, container, false)
        val context = activity

        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()

        // Getting the users department and making a database reference with it
        setInformation(email)
        val selectedDepartment = getShortDepartment()
        val modifiedEmail = email.replace('.', '-')
        var userSemester = "Not given"
        val pathSemester = "/user-details/$modifiedEmail"
        val databaseReferenceSemester = FirebaseDatabase.getInstance().getReference(pathSemester)
        val postListenerSemester = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.getValue<UserAllData>()

                if (post != null) {
                    userSemester = post.userSemester.toString()
                    val section = post.userSection.toString()
                    val year = userSemester[4]
                    val semester = userSemester[13]
                    val path = "/routines/$selectedDepartment/year$year/semester$semester/section$section"
                    var routineLink = "Not Found"
                    val databaseReference = FirebaseDatabase.getInstance().getReference(path)
                    val postListener = object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            val postRoutine = dataSnapshot.getValue<Routine>()


                            if (postRoutine != null) {
                                routineLink = postRoutine.image.toString()
                                if (context != null) {
                                    Glide.with(context).load(routineLink).into(viewBinding.images)
                                }
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
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

                makeToast("Error loading data")
            }
        }
        databaseReferenceSemester.addValueEventListener(postListenerSemester)


        return viewBinding.root
    }


}