package com.example.homepage.profileTab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homepage.databinding.FragmentSettingsBinding
import com.example.homepage.loginSignup.HelperSignInSignUp
import com.example.homepage.loginSignup.SignInActivity
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SettingsFragment : ReplaceFragment() {

    private var fragmentBinding: FragmentSettingsBinding? = null
    private val viewBinding get() = fragmentBinding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        container?.removeAllViews()
        fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)


        viewBinding.btnChangePassword.setOnClickListener {

            val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
            val currentUserEmail = user?.email.toString()
            val currentUserPassword = viewBinding.tvCurrentUserPassword.text.toString()
            val newUserPassword = viewBinding.tvNewUserPasswordType.text.toString()
            val newUserPasswordRetype = viewBinding.tvNewUserPasswordRetype.text.toString()
            val helper = this.context?.let {  HelperSignInSignUp() }
            val validStatus = helper?.validateEmailPasswordFormat(
                currentUserEmail,
                newUserPassword,
                newUserPasswordRetype
            )

            if (validStatus == "Valid Data") {
                fireBasePasswordChange(currentUserEmail, currentUserPassword, newUserPasswordRetype)
            } else {
                if (validStatus != null) {
                    makeToast(validStatus)
                }
                viewBinding.tvNewUserPasswordRetype.setText("")
            }


        }

        viewBinding.goToForgotPasswrdFragment.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToForgotPasswordFragment()
            findNavController().navigate(action)
        }
        return viewBinding.root
    }

// The method takes current users email (currentUserEmail), current users old password (oldUserPassword), new users password (newUserPassword) as parameter and change the user password to newUserPassword
    private fun fireBasePasswordChange(
        currentUserEmail: String,
        oldUserPassword: String,
        newUserPassword: String
    ) {
    // To re authenticate the user credentials getting current sign in credentials
        val credential: AuthCredential =
            EmailAuthProvider.getCredential(currentUserEmail, oldUserPassword)

    // creating current users instance
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser


    // creating after successfully re authenticating update password will be called else it will provide a toast about the error ( makeToast is a user defined function here for providing a toast to the user)
        user?.reauthenticate(credential)?.addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
                    user.updatePassword(newUserPassword).addOnCompleteListener {
                        if (it.isSuccessful) {
                            makeToast("Password updated")

                            // This part is optional
                            // it is signing out the user from the current status once changing password is successful
                            // it is changing the activity and going to the sign in page while clearing the backstack so the user cant come to the current state by back pressing

                            FirebaseAuth.getInstance().signOut()
                            val i = Intent(activity, SignInActivity::class.java)
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(i)
                            (activity as Activity?)!!.overridePendingTransition(0, 0)


                        } else
                            makeToast("Error password not updated")
                    }
                }
                else -> {
                    makeToast("Incorrect old password")
                }

            }
        }
    }

}
