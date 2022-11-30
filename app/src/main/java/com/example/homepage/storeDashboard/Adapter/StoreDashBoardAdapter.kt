package com.example.homepage.storeDashboard.Adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.storeTab.Model.Materials
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class StoreDashBoardAdapter(inflater: LayoutInflater) :
    RecyclerView.Adapter<StoreDashBoardAdapter.StoreDashBoardViewHolder>() {


    private val materials = ArrayList<Materials>()
    private var _inflater: LayoutInflater = inflater
    private lateinit var database: DatabaseReference


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreDashBoardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_storedashboard, parent, false)
        return StoreDashBoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreDashBoardViewHolder, position: Int) {
        val currentItem = materials[position]
        val context = holder.itemView.context
        holder.productName.text = currentItem.productName
        holder.productAuthorName.text = currentItem.productAuthor
        holder.bookPrice.text = currentItem.productPrice + " BDT"
        holder.productCategory.text = currentItem.productCategory
        val auth = Firebase.auth
        val user = auth.currentUser!!.uid
        database = Firebase.database.reference
        val userProductReference =
            FirebaseDatabase.getInstance().getReference("user-posted-items").child(user)
        val productID = currentItem.productId.toString()

        val publicPostReference = FirebaseDatabase.getInstance().getReference("public-posts")

        val sellersContactNo = currentItem.sellersDetails?.split(" ")?.get(0)
        holder.detailsButton.setOnClickListener {
            Toast.makeText(context, currentItem.sellersDetails, Toast.LENGTH_SHORT).show()
        }

        holder.deleteButton.setOnClickListener {
            userProductReference.child(productID).removeValue()
            publicPostReference.child(productID).removeValue()
        }

        holder.updateProductButton.setOnClickListener {
            val rootLayout = _inflater.inflate(R.layout.popup_add_item_for_sell, null)
            val productName = rootLayout.findViewById<EditText>(R.id.productNamePop)
            val productAuthor = rootLayout.findViewById<EditText>(R.id.productAuthorPop)
            val productPrice = rootLayout.findViewById<EditText>(R.id.productPricePop)
            val sellersContactNo2 = rootLayout.findViewById<EditText>(R.id.sellerContactNoPop)
            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
            val productDescription = rootLayout.findViewById<EditText>(R.id.productDetailsPop)
            val productCategory = rootLayout.findViewById<Spinner>(R.id.bookCategoryList)
            productName.setText(currentItem.productName.toString())
            productAuthor.setText(currentItem.productAuthor.toString())
            productPrice.setText(currentItem.productPrice.toString())
            productDescription.setText(currentItem.productDetails.toString())
            addButton.text = "UPDATE"
            sellersContactNo2.setText(sellersContactNo)


            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )
            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                holder.updateProductButton,
                Gravity.CENTER,
                0,
                -500
            )
            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {
                if (productName.text.toString() == "")
                    Toast.makeText(context, "Please fill up all Product Name", Toast.LENGTH_SHORT)
                        .show()
                else if (productCategory.selectedItem.toString() == "Category")
                    Toast.makeText(context, "Please fill up Product Category", Toast.LENGTH_SHORT)
                        .show()
                else if (productPrice.text.toString() == "")
                    Toast.makeText(context, "Please fill up Product Price", Toast.LENGTH_SHORT)
                        .show()
                else {

                    if (sellersContactNo != null) {
                        updateMaterial(
                            user,
                            productName.text.toString(),
                            productAuthor.text.toString(),
                            productCategory.selectedItem.toString(),
                            productPrice.text.toString(),
                            sellersContactNo,
                            currentItem.sellersDetails.toString(),
                            currentItem.productId.toString(),
                            productDescription.text.toString()

                        )
                    }
                    popupWindow.dismiss()
                }
            }

        }


    }

    private fun updateMaterial(
        user: String,
        productName: String,
        productAuthor: String,
        productCategory: String,
        productPrice: String,
        sellersContactNo: String,
        sellersDetails: String,
        key: String,
        productDetailsWrite: String,

        ) {
        val updateMaterial = Materials(
            user,
            productName,
            productAuthor,
            productCategory,
            productPrice,
            sellersContactNo,
            sellersDetails,
            key,
            productDetailsWrite
        )
        val taskValues = updateMaterial.toMap()
        val childUpdates = hashMapOf<String, Any>(
            "/user-posted-items/$user/$key" to taskValues,
            "/public-posts/$key" to taskValues
        )
        database.updateChildren(childUpdates)

    }

    override fun getItemCount(): Int {
        return materials.size
    }

    fun updateStoreList(materials: List<Materials>) {

        this.materials.clear()
        this.materials.addAll(materials)
        notifyDataSetChanged()

    }

    class StoreDashBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productNameCard)
        val productAuthorName: TextView = itemView.findViewById(R.id.productAuthorNameCard)
        val productCategory: TextView = itemView.findViewById(R.id.productCategoryCard)
        val bookPrice: TextView = itemView.findViewById(R.id.productPriceCard)
        val detailsButton: Button = itemView.findViewById(R.id.showDetailsButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteProductButton)
        val updateProductButton: Button = itemView.findViewById(R.id.updateProductButton)

    }
}