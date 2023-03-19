package com.example.homepage.courseTab.YouTubeVideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentYouTubeVideoBinding

class YouTubeVideoFragment : Fragment() {
    private var fragmentBinding: FragmentYouTubeVideoBinding? = null
    private val viewBinding get() = fragmentBinding!!


    private lateinit var recycler: RecyclerView
    private val adapter: YouTubeVideoAdapter by lazy { YouTubeVideoAdapter() }
    private val viewModel by viewModels<YouTubeVideoViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentYouTubeVideoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.updateVideoList(it)
        }
    }

    private fun setupRecyclerView() {
        recycler = viewBinding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter
    }


}