package com.example.homepage.features.loginSignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homepage.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private var helper = HelperSignInSignUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        viewBinding.signupBtn.setOnClickListener {

            val email = viewBinding.usersEmail.text.toString()
            val password = viewBinding.usersPasswordType.text.toString()
            val passwordRetype = viewBinding.usersPasswordRetype.text.toString()

            val validityStatus = helper.validateEmailPasswordFormat(email, password, passwordRetype)

            if (validityStatus == "Valid Data") {

                if (fireBaseSignup(email, password)) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }

            } else {
                makeToast(validityStatus)
                viewBinding.usersPasswordRetype.setText("")
            }
        }
        viewBinding.goToSignInPage.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }


    private fun fireBaseSignup(email: String, password: String): Boolean {
        var accountCreationStatus = false
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            accountCreationStatus = if (it.isSuccessful) {

                sendVerificationMail()
                makeToast("Account created with email $email")
                true

            } else {
                val exceptionMessage =
                    it.exception.toString().substring(it.exception.toString().indexOf(":") + 1)
                        .trim()
                makeToast(exceptionMessage)
                false
            }
        }

        return accountCreationStatus
    }

    private fun sendVerificationMail() {

        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->

            if (task.isSuccessful) {
                makeToast("Verification mail has been sent on the email ")
                makeToast("CHECK YOUR EMAILS SPAM BOX FOR VERIFICATION EMAIL")
            } else {

                makeToast("Error Occurred")
            }
        }
    }

    fun  makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }


}