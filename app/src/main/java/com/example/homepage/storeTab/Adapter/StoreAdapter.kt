package com.example.homepage.storeTab.Adapter

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
import com.example.homepage.storeTab.Model.Materials

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {
    private val materialIds = ArrayList<String>()
    private val materials = ArrayList<Materials>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_materials, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val currentItem = materials[position]
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


    override fun getItemCount(): Int {
        return materials.size
    }

    fun updateStoreList(materials: List<Materials>) {

        this.materials.clear()
        this.materials.addAll(materials)
        notifyDataSetChanged()

    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productNameCard)
        val productAuthorName: TextView = itemView.findViewById(R.id.productAuthorNameCard)
        val productCategory: TextView = itemView.findViewById(R.id.productCategoryCard)
        val bookPrice: TextView = itemView.findViewById(R.id.productPriceCard)
        val callSellerButton: Button = itemView.findViewById(R.id.callSellerButton)
        val detailsButton: Button = itemView.findViewById(R.id.showDetailsButton)

    }
}