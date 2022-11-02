package com.example.homepage.store

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentStoreBinding
import com.example.homepage.store.Model.Materials
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class StoreFragment : ReplaceFragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference
    private lateinit var materialReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MaterialAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        materialReference =
            FirebaseDatabase.getInstance().getReference("user-materials").child(user)
        recycler = binding.materialList

        recycler.layoutManager = LinearLayoutManager(context)


        binding.floatingPostMaterialButton.setOnClickListener {
            val rootLayout = layoutInflater.inflate(R.layout.popup_add_item_for_sell, null)

            val productName = rootLayout.findViewById<EditText>(R.id.productNamePop)
            val productAuthor = rootLayout.findViewById<EditText>(R.id.productAuthorPop)
            val productCategory = rootLayout.findViewById<EditText>(R.id.productCategoryPop)
            val productPrice = rootLayout.findViewById<EditText>(R.id.productPricePop)
            val sellersContactNo = rootLayout.findViewById<EditText>(R.id.sellerContactNoPop)
            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                binding.materialListActivity, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {

                val productNameWrite = productName.text.toString()
                val productAuthorWrite = productAuthor.text.toString()
                val productCategoryWrite = productCategory.text.toString()
                val productPriceWrite = productPrice.text.toString()
                val sellersContactNoWrite = sellersContactNo.text.toString()
                val sellersDetailsWrite = "Name  Email StudentId Admission Session Department"


                if (productNameWrite == "" || sellersContactNoWrite == "" || productAuthorWrite == "" || productPriceWrite =="" || productCategoryWrite =="")
                    Toast.makeText(
                        context,
                        "Please fill up all  the information",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    addNewMaterial(user, productNameWrite, productAuthorWrite,productCategoryWrite,productPriceWrite, sellersContactNoWrite,sellersDetailsWrite)
                popupWindow.dismiss()


            }


        }
        return binding.root
    }


    private fun addNewMaterial(
        userId: String,
        productName: String,
        productAuthor: String,
        productCategory: String,
        productPrice : String ,
        sellersContactNo : String,
        sellersDetails : String
    ) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        val key = database.child("posts").push().key

        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        val newMaterial = Materials(userId, productName, productAuthor, productCategory, productPrice,sellersContactNo  ,sellersDetails )
        val taskValues = newMaterial.toMap()
        val childUpdates = hashMapOf<String, Any>(
            //*   "/tasks/$key" to taskValues,
            "/user-materials/$userId/$key" to taskValues
//            "/user-materials/$key" to taskValues
        )
        database.updateChildren(childUpdates)

    }

    override fun onStart() {
        super.onStart()
        adapter = context?.let { MaterialAdapter(it, materialReference) }!!
        recycler.adapter = adapter
    }


    private class MaterialAdapter(
        private val context: Context,
        databaseReference: DatabaseReference
    ) : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>(){

        private val childEventListener: ChildEventListener?
        private val materialIds = ArrayList<String>()
        private val materials = ArrayList<Materials>()

        init {
            // Create child event listener
            // [START child_event_listener_recycler]
            val childEventListener = object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    Log.d("GetData", "onChildAdded:" + dataSnapshot.key!!)

                    // A new comment has been added, add it to the displayed list
                    val taskreceived = dataSnapshot.getValue<Materials>()

                    // [START_EXCLUDE]
                    // Update RecyclerView
                    materialIds.add(dataSnapshot.key!!)
                    materials.add(taskreceived!!)
                    notifyItemInserted(materials.size - 1)
                    // [END_EXCLUDE]
                }

                override fun onChildChanged(
                    dataSnapshot: DataSnapshot,
                    previousChildName: String?
                ) {
                    Log.d("GetData", "onChildChanged: ${dataSnapshot.key}")


                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    Log.d("GetData", "onChildRemoved:" + dataSnapshot.key!!)

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so remove it.
                    val commentKey = dataSnapshot.key

                    // [START_EXCLUDE]
                    val commentIndex = materialIds.indexOf(commentKey)
                    if (commentIndex > -1) {
                        // Remove data from the list
                        materialIds.removeAt(commentIndex)
                        materials.removeAt(commentIndex)

                        // Update the RecyclerView
                        notifyItemRemoved(commentIndex)
                    } else {
                        Log.w("GetData", "onChildRemoved:unknown_child:" + commentKey!!)
                    }
                    // [END_EXCLUDE]
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    Log.d("GetData", "onChildMoved:" + dataSnapshot.key!!)

                    // A comment has changed position, use the key to determine if we are
                    // displaying this comment and if so move it.
                    val movedComment = dataSnapshot.getValue<Materials>()
                    val commentKey = dataSnapshot.key

                    //notifyItemMoved(movedComment,)


                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("GetData", "postComments:onCancelled", databaseError.toException())
                    Toast.makeText(
                        context, "Database Error occurred here",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            databaseReference.addChildEventListener(childEventListener)
            // [END child_event_listener_recycler]
            // Store reference to listener so it can be removed on app stop
            this.childEventListener = childEventListener
        }

        class MaterialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val bookName: TextView = itemView.findViewById(R.id.bookNameCard)
            val bookWriterName: TextView = itemView.findViewById(R.id.bookWriterNameCard)
            val bookPrice: TextView = itemView.findViewById(R.id.bookPriceCard)
            val callSellerButton : Button = itemView.findViewById(R.id.callSellerButton)
            val detailsButton : Button = itemView.findViewById(R.id.showDetailsButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.card_materials, parent, false)
            return MaterialViewHolder(view)
        }

        override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
            val currentItem = materials[position]
            holder.bookName.text = currentItem.productName
            holder.bookWriterName.text = currentItem.productAuthor
            holder.bookPrice.text = currentItem.productCategory

            holder.callSellerButton .setOnClickListener {
                val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "01836430305"))
                context.startActivity(i)
            }
        }

        override fun getItemCount(): Int = materials.size

    }

}