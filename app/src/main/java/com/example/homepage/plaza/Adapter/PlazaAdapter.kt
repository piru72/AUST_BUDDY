package com.example.homepage.plaza.Adapter

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
import com.example.homepage.plaza.Model.Announcements

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
        holder.productName.text = currentItem.productName
        holder.productAuthorName.text = currentItem.productAuthor
        holder.bookPrice.text = currentItem.productPrice + " BDT"
        holder.productCategory.text = currentItem.productCategory
        val sellersContactNo = currentItem.sellersDetails?.split(" ")?.get(0)
        holder.detailsButton.setOnClickListener {
            Toast.makeText(context, currentItem.sellersDetails, Toast.LENGTH_SHORT).show()
        }
        holder.callSellerButton.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$sellersContactNo"))
            context.startActivity(i)
        }

    }


    // Total childs
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
        val productName: TextView = itemView.findViewById(R.id.productNameCard)
        val productAuthorName: TextView = itemView.findViewById(R.id.productAuthorNameCard)
        val productCategory: TextView = itemView.findViewById(R.id.productCategoryCard)
        val bookPrice: TextView = itemView.findViewById(R.id.productPriceCard)
        val callSellerButton: Button = itemView.findViewById(R.id.callSellerButton)
        val detailsButton: Button = itemView.findViewById(R.id.showDetailsButton)

    }
}