package com.example.homepage.loginSignup

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homepage.BaseActivity
import com.example.homepage.R
import com.example.homepage.dataClass.UserData
import com.example.homepage.databinding.ActivitySignInBinding
import com.example.homepage.profileTab.ForgotPasswordFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySignInBinding
    private var helper = HelperSignInSignUp(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        database = Firebase.database.reference

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Signing into your Account in few seconds...")
        progressDialog.setCanceledOnTouchOutside(false)



        binding.signinBtn.setOnClickListener {

            val email = binding.usersEmail.text.toString()
            val password = binding.usersPassword.text.toString()

            val validityStatus = helper.validateEmailPasswordFormat(email, password, password)

            if (validityStatus == "Valid Data") {
                fireBaseSignIn(email, password)
            } else {
                helper.makeToast(validityStatus)
            }
        }

        binding.goToSignUpPage.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.goToForgotPasswrdFragment.setOnClickListener {
            replaceFragment(ForgotPasswordFragment())
        }
    }

    public override fun onStart() {
        super.onStart()

        if (auth.currentUser?.isEmailVerified == true)
            updateUI(auth.currentUser)

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Log.i("LoginActivity", "Update UI Called")
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun writeNewUser(userId: String, name: String, email: String?) {
        val user = UserData(name, email)
        database.child("users").child(userId).setValue(user)
    }

    private fun usernameFromEmail(email: String): String {
        return if (email.contains("@")) {
            email.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        } else {
            email
        }
    }

    private fun onAuthSuccess(user: FirebaseUser) {
        val username = usernameFromEmail(user.email!!)
        writeNewUser(user.uid, username, user.email)
    }


    private fun fireBaseSignIn(email: String, password: String) {
        progressDialog.show()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
                    progressDialog.dismiss()

                    if (auth.currentUser?.isEmailVerified == true) {
                        updateUI(auth.currentUser)
                        onAuthSuccess(task.result?.user!!)
                    } else
                        helper.makeToast("Please verify your email")
                }
                else -> {
                    helper.makeToast("Login unsuccessful wrong email or password")
                    progressDialog.dismiss()
                }
            }
        }


    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.activity_sign_in, fragment)
        fragmentTransaction.addToBackStack("tag").commit()

    }

}
