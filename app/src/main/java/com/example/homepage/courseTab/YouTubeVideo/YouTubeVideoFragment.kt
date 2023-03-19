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


    private val recyclerView: RecyclerView by lazy { viewBinding.recyclerView }

    private val viewModel by viewModels<YouTubeVideoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        fragmentBinding = FragmentYouTubeVideoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        val adapter = YouTubeVideoAdapter()


        recyclerView.adapter = adapter


        viewModel.liveData.observe(viewLifecycleOwner) {

            adapter.updateVideoList(it)

        }
    }


}