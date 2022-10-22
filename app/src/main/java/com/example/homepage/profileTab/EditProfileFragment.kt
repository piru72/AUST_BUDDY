package com.example.homepage.profileTab

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth


class EditProfileFragment : ReplaceFragment() {

    private val user = FirebaseAuth.getInstance().currentUser
    private var latitude =0.0
    private var longitude =0.0
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        fusedLocationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!
        val email = user?.email.toString()
        setInformation(email)

        val userId = v.findViewById<TextView>(R.id.IdReal)
        val userName = v.findViewById<TextView>(R.id.userNameReal)
        val userFullName = v.findViewById<TextView>(R.id.fullNameReal)
        val userDept = v.findViewById<TextView>(R.id.departmentReal)
        val userSession = v.findViewById<TextView>(R.id.sessionReal)

        userId.text = getUserId()
        userFullName.text = getUserEmail()
        userDept.text = getDepartment()
        userSession.text = getSession()
        userName.text = getUserName()
        obtainedLocalization()

        makeToast(latitude.toString())
        makeToast(longitude.toString())

        return v
    }


    private fun obtainedLocalization(){
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                latitude = location?.latitude!!
                longitude = location.longitude
            }
    }


}