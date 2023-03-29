package com.example.homepage.features.plazaTab.plazaDashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentPlazaDashBoardBinding
import com.example.homepage.features.plazaTab.plazaDashboard.Adapter.PlazaDashBoardAdapter
import com.example.homepage.features.plazaTab.plazaDashboard.Model.PlazaDashBoardViewModel
import com.example.homepage.utils.helpers.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PlazaDashBoardFragment : ReplaceFragment() {
    private lateinit var fragmentBinding: FragmentPlazaDashBoardBinding
    private val viewBinding get() = fragmentBinding

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var recycler: RecyclerView
    private var adapter: PlazaDashBoardAdapter? = null
    private lateinit var viewModel: PlazaDashBoardViewModel
    private lateinit var _inflater: LayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        fragmentBinding = FragmentPlazaDashBoardBinding.inflate(inflater, container, false)
        _inflater = inflater
        auth = Firebase.auth
        database = Firebase.database.reference


        return viewBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = viewBinding.postedItemList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = PlazaDashBoardAdapter(_inflater)
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[PlazaDashBoardViewModel::class.java]
        viewModel.allUserAnnouncements.observe(viewLifecycleOwner) {
            adapter!!.updatePlazaDashboardList(it)
        }

    }


}