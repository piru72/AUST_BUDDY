package com.example.homepage.profileTab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.homepage.R

class BugReport : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_bug_report, container, false)

        val sendBTn = v.findViewById<Button>(R.id.bugReportBtn)

        sendBTn.setOnClickListener {
            val email = "unibuddy890@gmail.com"
            val message = v.findViewById<TextView>(R.id.message)
            val addresses = email.split(",".toRegex()).toTypedArray()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, "FIX THE BUG OF UNIBUDDY")
                putExtra(Intent.EXTRA_TEXT,  message.text.toString())
            }
            startActivity(intent)
            message.text =" "
        }
        return v
    }
}