package com.example.homepage.favouriteWebPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.FragmentFavouriteWebPageBinding


class FavouriteWebPageFragment : Fragment() {
   private  var _binding : FragmentFavouriteWebPageBinding? = null
   private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentFavouriteWebPageBinding.inflate(inflater, container, false)
       
        return binding.root
    }

    
}