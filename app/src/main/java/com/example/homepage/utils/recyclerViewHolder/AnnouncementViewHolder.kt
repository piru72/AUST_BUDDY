package com.example.homepage.utils.recyclerViewHolder

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.CardAnnouncementsBinding
import com.example.homepage.features.plazaTab.DialogViewDetails
import com.example.homepage.utils.models.Announcements

class AnnouncementViewHolder(private val binding: CardAnnouncementsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(announcement: Announcements) {
        val context = binding.root.context

        binding.productNameCard.text = announcement.sellersDetails?.split(" ")?.get(1)
        binding.productAuthorNameCard.text = announcement.productAuthor
        binding.productCategoryCard.text = announcement.productName
//        binding.announcementDetails.text = announcement.productDetails

        val sellersContactNo = announcement.sellersDetails?.split(" ")?.get(0)

        binding.showDetailsButton.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val addAnnouncementBottomSheetFragment = DialogViewDetails(announcement.sellersDetails)
            addAnnouncementBottomSheetFragment.show(activity.supportFragmentManager, addAnnouncementBottomSheetFragment.tag)
        }
        binding.callSellerButton.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$sellersContactNo"))
            context.startActivity(i)
        }
    }
}