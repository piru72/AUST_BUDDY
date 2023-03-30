package com.example.homepage.features.profileTab.adminPanel.bugReports.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardBugreportBinding
import com.example.homepage.utils.models.BugReportsData

class BugReportAdapter : RecyclerView.Adapter<BugReportViewHolder>() {
    private val _itemList = ArrayList<BugReportsData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BugReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardBugreportBinding.inflate(inflater, parent, false)
        return BugReportViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    override fun onBindViewHolder(holder: BugReportViewHolder, position: Int) {
        val currentItem = _itemList[position]
        val context = holder.itemView.context
        holder.bind(currentItem,context)

    }


    fun updateBugReportList(bugReports: List<BugReportsData>) {
        this._itemList.clear()
        this._itemList.addAll(bugReports)
        notifyDataSetChanged()

    }


}