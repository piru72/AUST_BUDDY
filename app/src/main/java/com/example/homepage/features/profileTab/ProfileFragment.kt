package com.example.homepage.features.profileTab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentProfileBinding
import com.example.homepage.features.onBoarding.authentication.SignInActivity
import com.example.homepage.network.cache.SharedPreference
import com.example.homepage.utils.helpers.ReplaceFragment
import com.example.homepage.utils.models.Admin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue


class ProfileFragment : ReplaceFragment() {

    private var fragmentBinding: FragmentProfileBinding? = null
    private val viewBinding get() = fragmentBinding!!

    private var isAdmin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()

        // Getting the users department and making a database reference with it
        setInformation(email)
        val modifiedEmail = email.replace('.', '-')
        var userEmail = "Not given"

        val path = "/admin-list/$modifiedEmail"
        val databaseReference = FirebaseDatabase.getInstance().getReference(path)

        // Creating a shared Preference object to cache the data into the local storage and instead of doing a network call each time we are checking the
        val sharePref = SharedPreference();
        isAdmin = context?.let { sharePref.getAdminDetailsFromCache(it) }.toString()

        // if the sharedPreference doesn't have a value stored then we are
        if (isAdmin == "")
        {
            //makeToast("Fetching from the network")
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val post = dataSnapshot.getValue<Admin>()

                    if (post != null) {
                        userEmail = post.email.toString()
                        if (userEmail == email) {
                            viewBinding.btnAdminPanel.visibility = View.VISIBLE
                            context?.let { sharePref.saveAdminDetailsToCache(it,"true") }
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                    Toast.makeText(context, "Error loading user data", Toast.LENGTH_SHORT).show()
                }
            }
            databaseReference.addValueEventListener(postListener)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        fragmentBinding = FragmentProfileBinding.inflate(inflater, container, false)

        // Getting the users email

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isAdmin == "true")
            viewBinding.btnAdminPanel.visibility = View.VISIBLE
        viewBinding.btnAdminPanel.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToAdminPanelFragment()
            findNavController().navigate(action)
        }
        viewBinding.btnEditProfile.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToEditProfileFragment2()
            findNavController().navigate(action)
        }
        viewBinding.btnPrivacy.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToPrivacyFragment()
            findNavController().navigate(action)
        }
        viewBinding.btnSettings.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToSettingsFragment()
            findNavController().navigate(action)
        }
        viewBinding.btnInviteFriend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Download this app for AUSTIANS\nhttps://play.google.com/store/apps/details?id=com.freondevs.homepage"
            )
            context?.startActivity(Intent.createChooser(intent, "Share"))
        }
        viewBinding.btnAboutUs.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToAboutDevFragment()
            findNavController().navigate(action)
        }
        viewBinding.btnLogOut.setOnClickListener {

            //  GOING FROM FRAGMENT TO ACTIVITY
            FirebaseAuth.getInstance().signOut()
            val i = Intent(activity, SignInActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
            (activity as Activity?)!!.overridePendingTransition(0, 0)

        }
    }


}


