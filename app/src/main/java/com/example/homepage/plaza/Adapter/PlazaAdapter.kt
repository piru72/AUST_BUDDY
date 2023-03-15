package com.example.homepage.plaza.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.plaza.DialogViewDetails
import com.example.homepage.Model.Announcements

class PlazaAdapter : RecyclerView.Adapter<PlazaAdapter.AnnouncementViewHolder>() {
    private val announcements = ArrayList<Announcements>()


    // Attaching the layout that will hold the announcements and populate them in the recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_announcements ,parent, false)
        return AnnouncementViewHolder(view)
    }

    // Attaching the data with the card
    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val currentItem = announcements[position]
        val context = holder.itemView.context

        holder.announcersName.text = currentItem.sellersDetails?.split(" ")?.get(1)

        holder.announcementsTopic.text = currentItem.productAuthor
        holder.announcementDate.text = currentItem.productName
        holder.announcementDetails.text = currentItem.productDetails

        val sellersContactNo = currentItem.sellersDetails?.split(" ")?.get(0)

        holder.detailsButton.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val addAnnouncementBottomSheetFragment = DialogViewDetails(currentItem.sellersDetails)
            addAnnouncementBottomSheetFragment.show(activity.supportFragmentManager, addAnnouncementBottomSheetFragment.tag)
        }
        holder.callButton.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$sellersContactNo"))
            context.startActivity(i)
        }

    }


    // Total child
    override fun getItemCount(): Int {
        return announcements.size
    }


    // Realtime update of the announcements
    fun updateAnnouncementList(materials: List<Announcements>) {

        this.announcements.clear()
        this.announcements.addAll(materials)
        notifyDataSetChanged()

    }


    // Class to hold the items of the card
    class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val announcersName: TextView = itemView.findViewById(R.id.productNameCard)
        val announcementsTopic: TextView = itemView.findViewById(R.id.productAuthorNameCard)
        val announcementDate: TextView = itemView.findViewById(R.id.productCategoryCard)
        val announcementDetails: TextView= itemView.findViewById(R.id.announcementDetails)
        val callButton: Button = itemView.findViewById(R.id.callSellerButton)
        val detailsButton: Button = itemView.findViewById(R.id.showDetailsButton)

    }
}