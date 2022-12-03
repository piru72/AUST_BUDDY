package com.example.homepage.profileTab

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.databinding.FragmentForgotPasswordBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordFragment : ReplaceFragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        container?.removeAllViews()

        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        binding.btnForgotPasswordSendMail.setOnClickListener {
            val userEmail = binding.recoveryEmail.text.toString()

            val validityStatus = validateEmail(userEmail)

            if (validityStatus == "Valid Data") {

                var mAuth: FirebaseAuth? = null

                mAuth = FirebaseAuth.getInstance()
                mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        makeToast("Password reset mail sent")
                    } else {
                        makeToast("Unsuccessful attempt ")
                    }

                }
                binding.recoveryEmail.setText("")

            } else {
                makeToast(validityStatus)
            }


        }
        return binding.root
    }

    private fun validateEmail(email: String): String {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            "Invalid email format"
        else if (email.length >= 35)
            "Too long characters"
        else if (!email.contains("@aust.edu"))
            "Provide your @aust.edu email"
        else
            "Valid Data"
    }
}