package com.example.homepage.helperClass


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.homepage.R
import com.example.homepage.databinding.FragmentYoutubeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class YoutubeFragment : Fragment() {

    private var fragmentBinding: FragmentYoutubeBinding? = null
    private val viewBinding get() = fragmentBinding!!
    private lateinit var navView: BottomNavigationView
    private val args: YoutubeFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navView = requireActivity().findViewById(R.id.nav_view)
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
    }


    private fun setupYoutubeLink() {

        viewBinding.apply {
            lifecycle.addObserver(youtubePlayerView)

            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    val videoId = args.videoId
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

            youtubePlayerView.enterFullScreen();


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


}