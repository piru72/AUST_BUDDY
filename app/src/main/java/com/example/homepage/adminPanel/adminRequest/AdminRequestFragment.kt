package com.example.homepage.adminPanel.adminRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.adminPanel.adminRequest.Adapter.AdminReqeustAdapter
import com.example.homepage.adminPanel.adminRequest.Model.AdminRequestViewModel
import com.example.homepage.databinding.FragmentAdminRequestBinding


class AdminRequestFragment : Fragment() {


    private var _binding: FragmentAdminRequestBinding? = null
    private val binding get() = _binding!!
    private lateinit var adminRequestRecyclerView: RecyclerView

    lateinit var adapter: AdminReqeustAdapter
    private lateinit var viewModel: AdminRequestViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAdminRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adminRequestRecyclerView = binding.recyclerView
        adminRequestRecyclerView.layoutManager = LinearLayoutManager(context)
        adminRequestRecyclerView.setHasFixedSize(true)
        adapter = AdminReqeustAdapter()
        adminRequestRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[AdminRequestViewModel::class.java]

        viewModel.allAdminRequest.observe(viewLifecycleOwner) {
            adapter.updateAdminRequestList(it)
        }

    }

}