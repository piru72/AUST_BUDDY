package com.example.homepage.homeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.homepage.R
import com.example.homepage.helperClass.ReplaceFragment


class BusFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_bus_schedule, container, false)

        // Used Image slider library https://github.com/denzcoskun/ImageSlideshow#android-image-slide
        val imageList = ArrayList<SlideModel>() // Create image list


        imageList.add(
            SlideModel(
                "https://raw.githubusercontent.com/piru72/Uni_buddy/master/Bus_Schedule/jamuna.jpeg",
                "Jamuna"
            )
        )
        imageList.add(
            SlideModel(
                "https://raw.githubusercontent.com/piru72/Uni_buddy/master/Bus_Schedule/karnafali.jpeg",
                "Karnafali"
            )
        )
        imageList.add(
            SlideModel(
                "https://raw.githubusercontent.com/piru72/Uni_buddy/master/Bus_Schedule/kopotakkho.jpeg",
                "Kopotakkho"
            )
        )
        imageList.add(
            SlideModel(
                "https://raw.githubusercontent.com/piru72/Uni_buddy/master/Bus_Schedule/meghna.jpeg",
                "Meghna"
            )
        )
        imageList.add(
            SlideModel(
                "https://raw.githubusercontent.com/piru72/Uni_buddy/master/Bus_Schedule/padma.jpeg",
                "Padma"
            )
        )
        imageList.add(
            SlideModel(
                "https://raw.githubusercontent.com/piru72/Uni_buddy/master/Bus_Schedule/surma.jpeg",
                "Surma"
            )
        )

        val imageSlider = v.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.stopSliding()

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                // You can listen here
            }
        })



        return v
    }
}