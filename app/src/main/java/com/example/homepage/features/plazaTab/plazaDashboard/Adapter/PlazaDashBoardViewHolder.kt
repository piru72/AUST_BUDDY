package com.example.homepage.features.plazaTab.plazaDashboard.Adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.database.FirebaseUtils
import com.example.homepage.databinding.CardPlazaDashboardBinding
import com.example.homepage.utils.models.Announcements

class PlazaDashBoardViewHolder(private val binding: CardPlazaDashboardBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(announcement: Announcements, _inflater: LayoutInflater) {

        binding.productNameCard.text = announcement.sellersDetails?.split(" ")?.get(1)
        binding.productAuthorNameCard.text = announcement.productAuthor
        binding.announcementDetails.text = announcement.productDetails


        val user = FirebaseUtils.getUserId()

        val productID = announcement.productId.toString()

        val announcementsCategory = announcement.productCategory

        val childHelper = ChildUpdaterHelper()
        binding.deleteProductButton.setOnClickListener {

            val parenUserPosted = "/user-posted-items/$user"
            val parenPublicAnnouncements = "public-announcements/$announcementsCategory"
            val parentAllAnnouncements = "/public-announcements/all"

            childHelper.removeChild(parentAllAnnouncements, productID)
            childHelper.removeChild(parenUserPosted, productID)
            childHelper.removeChild(parenPublicAnnouncements, productID)

        }


    }

}