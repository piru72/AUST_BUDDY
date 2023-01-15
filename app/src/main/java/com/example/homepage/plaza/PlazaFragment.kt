package com.example.homepage.plaza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentPlazaBinding
import com.example.homepage.plaza.Adapter.PlazaAdapter
import com.example.homepage.plaza.Model.PlazaViewModel
import com.example.homepage.superClass.ReplaceFragment


class PlazaFragment : ReplaceFragment() {


    private var _binding: FragmentPlazaBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PlazaViewModel
    private lateinit var recycler: RecyclerView
    private var adapter: PlazaAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlazaBinding.inflate(inflater, container, false)

        binding.btnPlazaDashboard.setOnClickListener {
            val action = PlazaFragmentDirections.actionPlazaFragmentToPlazaDashBoardFragment()
            findNavController().navigate(action)
        }
        binding.floatingPostItemButton.setOnClickListener {

            val bottomSheetFragment = DialogAddAnnouncement()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
        return binding.root
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