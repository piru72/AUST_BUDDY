package com.example.homepage.features.homeTab.favouriteWebPage.Adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.database.ChildUpdaterHelper
import com.example.homepage.database.FirebaseUtils
import com.example.homepage.databinding.CardFavouriteWebpageBinding
import com.example.homepage.features.homeTab.favouriteWebPage.FavouriteWebPageFragmentDirections
import com.example.homepage.utils.models.FavouriteWebpageData


class FavouriteWebpageViewHolder(private val binding: CardFavouriteWebpageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(task: FavouriteWebpageData) {
        binding.websiteNameCard.text = task.websiteName

        binding.websiteNameCard.setOnClickListener {
            val websiteLinkClick = task.websiteLink.toString()
            val action =
                FavouriteWebPageFragmentDirections.actionFavouriteWebPageFragmentToWebView2(
                    websiteLinkClick,
                    "View"
                )
            val navController = Navigation.findNavController(binding.root)
            navController.navigate(action)
        }
        binding.deleteWebsiteButton.setOnClickListener {

            val childHelper = ChildUpdaterHelper()
            val user = FirebaseUtils.getUserId()
            val websiteID = task.websiteID.toString()
            val parentNode = "/user-favouriteWebsites/$user"
            childHelper.removeChild(parentNode, websiteID)

        }
    }
}
