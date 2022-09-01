package com.example.homepage.profileTab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment
import org.intellij.lang.annotations.RegExp


class InviteFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_invite, container, false)

        val sendBTn = v.findViewById<Button>(R.id.sendBtn)

        //TODO validate message box , subject box , email box make a toast

        sendBTn.setOnClickListener{

            val email = v.findViewById<TextView>(R.id.emailAddress)
            val subject = v.findViewById<TextView>(R.id.subject)
            val message = v.findViewById<TextView>(R.id.message)

            val addresses = email.text.split(",".toRegex()).toTypedArray()

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject.text.toString())
                putExtra(Intent.EXTRA_TEXT, message.text.toString())
            }
            startActivity(intent)
        }
        fun isValidEmail(target :String): Boolean {
            return if (TextUtils.isEmpty(target)) {
                false;
            } else {
                android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
            }
        }



        return v
    }


}