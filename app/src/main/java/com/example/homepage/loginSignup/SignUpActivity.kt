package com.example.homepage.loginSignup

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homepage.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupBtn.setOnClickListener {

            val email = binding.usersEmail.text.toString()
            val password = binding.usersPasswordType.text.toString()
            val passwordRetype = binding.usersPasswordRetype.text.toString()

            val validityStatus = validateEmailPasswordFormat(email, password, passwordRetype)

            if (validityStatus == "Valid Data") {

                if (fireBaseSignup(email, password)) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }

            } else {
                makeToast(validityStatus)
                binding.usersPasswordRetype.setText("")
            }
        }
        binding.goToSignInPage.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validateEmailPasswordFormat(
        email: String,
        pass: String,
        passRetype: String
    ): String {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return "Invalid email format"
        else if (email.length >= 35)
            return "Too long characters"
        else if (!email.contains("@aust.edu"))
            return "Provide your @aust.edu email"
        else if (TextUtils.isEmpty(pass))
            return "Enter a password"
        else if (pass.length <= 6)
            return "Password is too short"
        else if (pass != passRetype)
            return "Passwords didn't match retype passwords"
        else if (pass.contains("@") || pass.contains("#") || pass.contains("%") || pass.contains("$") || pass.contains(
                "*"
            )
        )
            return "Valid Data"
        else
            return "Give a special character such as @,$,#.."

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

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}