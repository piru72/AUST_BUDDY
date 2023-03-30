package com.example.homepage.utils.helpers


import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
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


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentBinding = FragmentYoutubeBinding.inflate(inflater, container, false)

        val windowInsetsController = activity?.window?.insetsController
        windowInsetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navView = requireActivity().findViewById(R.id.nav_view)
        setupYoutubeLink()
    }


    private fun setupYoutubeLink() {

        viewBinding.apply {
            lifecycle.addObserver(youtubePlayerView)

            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = args.videoId
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

            youtubePlayerView.enterFullScreen()


        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
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