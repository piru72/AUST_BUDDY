package com.example.homepage.helperClass

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.homepage.R
import com.example.homepage.databinding.FragmentYoutubeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class YoutubeFragment : Fragment() {

    private var fragmentBinding: FragmentYoutubeBinding? = null
    private val viewBinding get() = fragmentBinding!!

    private lateinit var navView: BottomNavigationView
    private val args: YoutubeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentBinding = FragmentYoutubeBinding.inflate(inflater, container, false)

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupYoutubeLink()
        setupBottomNavigationView()
    }


    private fun setupYoutubeLink() {

        viewBinding.apply {
            webViewYoutube.settings.javaScriptEnabled = true
            webViewYoutube.loadUrl("https://www.youtube.com/embed/${args.videoId}")

        }
    }

    private fun setupBottomNavigationView() {
        navView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)

        navView.apply {
            visibility = View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        fragmentBinding = null
    }

    override fun onResume() {
        super.onResume()
        navView.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        navView.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navView = requireActivity().findViewById(R.id.nav_view)
    }


}