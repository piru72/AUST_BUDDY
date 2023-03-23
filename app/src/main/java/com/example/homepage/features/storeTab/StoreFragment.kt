package com.example.homepage.features.storeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentStoreBinding
import com.example.homepage.features.storeTab.Adapter.StoreAdapter
import com.example.homepage.features.storeTab.Model.StoreViewModel
import com.example.homepage.utils.helpers.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class StoreFragment : ReplaceFragment() {
    private var fragmentBinding: FragmentStoreBinding? = null
    private val viewBinding get() = fragmentBinding!!

    private lateinit var viewModel: StoreViewModel
    private lateinit var recycler: RecyclerView
    private var adapter: StoreAdapter? = null
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentStoreBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = viewBinding.materialList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = StoreAdapter()
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[StoreViewModel::class.java]
        viewModel.allStore.observe(viewLifecycleOwner) {
            adapter!!.updateStoreList(it)
        }

    }


}