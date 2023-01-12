package com.example.homepage.plazaDashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentPlazaDashBoardBinding
import com.example.homepage.storeDashboard.Adapter.StoreDashBoardAdapter
import com.example.homepage.storeDashboard.Model.StoreDashBoardViewModel
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PlazaDashBoardFragment : ReplaceFragment() {
    private lateinit var _binding: FragmentPlazaDashBoardBinding
    private val binding get() = _binding

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var recycler: RecyclerView
    private var adapter: StoreDashBoardAdapter? = null
    private lateinit var viewModel: StoreDashBoardViewModel
    private lateinit var _inflater: LayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentPlazaDashBoardBinding.inflate(inflater, container, false)
        _inflater = inflater
        auth = Firebase.auth
        database = Firebase.database.reference


        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.postedItemList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = StoreDashBoardAdapter(_inflater)
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[StoreDashBoardViewModel::class.java]
        viewModel.allStore.observe(viewLifecycleOwner) {
            adapter!!.updateStoreList(it)
        }

    }


}