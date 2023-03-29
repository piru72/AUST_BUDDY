package com.example.homepage.features.profileTab.adminPanel.bugReports.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.utils.models.BugReportsData
import com.google.firebase.database.FirebaseDatabase

class BugReportAdapter : RecyclerView.Adapter<BugReportAdapter.BugReportViewHolder>() {
    private val bugReports = ArrayList<BugReportsData>()

    override fun getItemCount(): Int {
        return bugReports.size
    }

    override fun onBindViewHolder(holder: BugReportViewHolder, position: Int) {
        val currentBugReport = bugReports[position]
        val context = holder.itemView.context
        holder.bugReportDetails.text = currentBugReport.reportDetails

        val bugReportReference = FirebaseDatabase.getInstance().getReference("admin-bug-reports")

        holder.bugReportersDetails.setOnClickListener {
            Toast.makeText(context, currentBugReport.reportersDetails, Toast.LENGTH_SHORT).show()
        }
        holder.doneBugReport.setOnClickListener {

            val email = currentBugReport.reportersDetails
            val addresses = email?.split(",".toRegex())?.toTypedArray()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, "BUG REPORT UPDATE")
                putExtra(
                    Intent.EXTRA_TEXT,
                    " The bug you mentioned about --->>>" + currentBugReport.reportDetails + " <<<---has been fixed . Thanks for your participation . We value your findings and feel free to provide more updates / suggestions  \n\nThank You\nUnibuddy team"
                )
            }
            context.startActivity(intent)
            bugReportReference.child(currentBugReport.key.toString()).removeValue()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BugReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_bugreport, parent, false)
        return BugReportViewHolder(view)
    }

    fun updateBugReportList(bugReports: List<BugReportsData>) {
        this.bugReports.clear()
        this.bugReports.addAll(bugReports)
        notifyDataSetChanged()

    }

    class BugReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bugReportDetails: TextView = itemView.findViewById(R.id.bugReportDetailsCard)
        val bugReportersDetails: Button = itemView.findViewById(R.id.showReportersDetailsButton)
        val doneBugReport: Button = itemView.findViewById(R.id.deleteBugReportButton)
    }
}