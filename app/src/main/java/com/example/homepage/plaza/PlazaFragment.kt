package com.example.homepage.plaza

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentPlazaBinding
import com.example.homepage.plaza.Adapter.PlazaAdapter
import com.example.homepage.plaza.Model.PlazaViewModel
import com.example.homepage.storeTab.Model.Materials
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PlazaFragment : ReplaceFragment() {


    private var _binding: FragmentPlazaBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PlazaViewModel
    private lateinit var recycler: RecyclerView
    private var adapter: PlazaAdapter? = null
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val userV = FirebaseAuth.getInstance().currentUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlazaBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference

        binding.btnPlazaDashboard.setOnClickListener {
            val action = PlazaFragmentDirections.actionPlazaFragmentToPlazaDashBoardFragment()
            findNavController().navigate(action)
        }
        val user = auth.currentUser!!.uid

        binding.floatingPostItemButton.setOnClickListener {
            val rootLayout = layoutInflater.inflate(R.layout.popup_add_item_for_sell, null)

            val productName = rootLayout.findViewById<EditText>(R.id.productNamePop)
            val productAuthor = rootLayout.findViewById<EditText>(R.id.productAuthorPop)
            val productCategory = rootLayout.findViewById<Spinner>(R.id.bookCategoryList)
            val productPrice = rootLayout.findViewById<EditText>(R.id.productPricePop)
            val productDetails = rootLayout.findViewById<EditText>(R.id.productDetailsPop)
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

                binding.postedItemsListLayout,
                Gravity.CENTER,
                0,
                -500
            )

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }


            addButton.setOnClickListener {

                val productNameWrite = productName.text.toString()
                val productAuthorWrite = productAuthor.text.toString()
                val productCategoryWrite = productCategory.selectedItem.toString()
                val productPriceWrite = productPrice.text.toString()
                val productDetailsWrite = productDetails.text.toString()
                val sellersContactNoWrite = sellersContactNo.text.toString()
                val email = userV?.email.toString()
                setInformation(email)
                val sellersDetailsWrite =
                    sellersContactNoWrite + " " + getUserName() + " " + getUserEmail() + " " + getUserId() + " " + getSession() + " " + getDepartment()


                if (productNameWrite == "")
                    makeToast("Please fill up all Product Name")
                else if (productCategoryWrite == "Category")
                    makeToast("Please fill up Product Category")
                else if (productPriceWrite == "")
                    makeToast("Please fill up Product Price")
                else if (!validNumber(sellersContactNoWrite))
                    makeToast("Provide 11 digit valid phone number")
                else {

                    addNewMaterial(
                        user,
                        productNameWrite,
                        productAuthorWrite,
                        productCategoryWrite,
                        productPriceWrite,
                        sellersContactNoWrite,
                        sellersDetailsWrite,
                        productDetailsWrite
                    )
                    popupWindow.dismiss()
                }


            }


        }
        return binding.root
    }

    private fun addNewMaterial(
        userId: String,
        productName: String,
        productAuthor: String,
        productCategory: String,
        productPrice: String,
        sellersContactNo: String,
        sellersDetails: String,
        productDetailsWrite: String
    ) {
        val key = database.child("posts").push().key

        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        val newMaterial = Materials(
            userId,
            productName,
            productAuthor,
            productCategory,
            productPrice,
            sellersContactNo,
            sellersDetails,
            key,
            productDetailsWrite
        )
        val taskValues = newMaterial.toMap()
        val childUpdates = hashMapOf<String, Any>(
            "/user-posted-items/$userId/$key" to taskValues,
            "/public-posts/$key" to taskValues
        )
        database.updateChildren(childUpdates)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.announcementList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = PlazaAdapter()
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[PlazaViewModel::class.java]
        viewModel.allStore.observe(viewLifecycleOwner) {
            adapter!!.updateStoreList(it)
        }

    }

}