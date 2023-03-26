package com.example.homepage.features.authentication

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homepage.R
import com.example.homepage.app.BaseActivity
import com.example.homepage.databinding.ActivitySignInBinding
import com.example.homepage.utils.helpers.HelperSignInSignUp
import com.example.homepage.utils.models.UserData
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
    private lateinit var viewBinding: ActivitySignInBinding
    private var helper = HelperSignInSignUp()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivitySignInBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        auth = Firebase.auth
        database = Firebase.database.reference

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Signing into your Account in few seconds...")
        progressDialog.setCanceledOnTouchOutside(false)



        viewBinding.signinBtn.setOnClickListener {

            val email = viewBinding.usersEmail.text.toString()
            val password = viewBinding.usersPassword.text.toString()

            val validityStatus = helper.validateEmailPasswordFormat(email, password, password)

            if (validityStatus == "Valid Data") {
                fireBaseSignIn(email, password)
            } else {
                makeToast(validityStatus)
            }
        }

        viewBinding.goToSignUpPage.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        viewBinding.goToForgotPasswrdFragment.setOnClickListener {
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
                        makeToast("Please verify your email")
                }
                else -> {
                    makeToast("Login unsuccessful wrong email or password")
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

    fun  makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

}
