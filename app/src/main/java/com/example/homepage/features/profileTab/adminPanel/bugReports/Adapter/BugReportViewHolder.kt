package com.example.homepage.features.profileTab.adminPanel.bugReports.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.CardBugreportBinding
import com.example.homepage.utils.models.BugReportsData
import com.google.firebase.database.FirebaseDatabase

class BugReportViewHolder(private val binding : CardBugreportBinding) : RecyclerView.ViewHolder(binding.root) {
    val bugReportDetails: TextView = itemView.findViewById(R.id.bugReportDetailsCard)
    val bugReportersDetails: Button = itemView.findViewById(R.id.showReportersDetailsButton)
    val doneBugReport: Button = itemView.findViewById(R.id.deleteBugReportButton)


    fun bind (bugReport: BugReportsData, context: Context){

        binding.bugReportDetailsCard.text = bugReport.reportDetails

        val bugReportReference = FirebaseDatabase.getInstance().getReference("admin-bug-reports")

        binding.showReportersDetailsButton.setOnClickListener {
            Toast.makeText(context, bugReport.reportersDetails, Toast.LENGTH_SHORT).show()
        }
        binding.deleteBugReportButton.setOnClickListener {

            val email = bugReport.reportersDetails
            val addresses = email?.split(",".toRegex())?.toTypedArray()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, "BUG REPORT UPDATE")
                putExtra(
                    Intent.EXTRA_TEXT,
                    " The bug you mentioned about --->>>" + bugReport.reportDetails + " <<<---has been fixed . Thanks for your participation . We value your findings and feel free to provide more updates / suggestions  \n\nThank You\nUnibuddy team"
                )
            }
            context.startActivity(intent)
            bugReportReference.child(bugReport.key.toString()).removeValue()
        }
    }
}