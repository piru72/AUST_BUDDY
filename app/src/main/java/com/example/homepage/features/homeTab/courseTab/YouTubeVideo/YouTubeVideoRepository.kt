package com.example.homepage.features.homeTab.courseTab.YouTubeVideo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.utils.models.YouTubeVideoData
import com.google.firebase.database.*

class YouTubeVideoRepository(database: FirebaseDatabase) {
    private var databaseParentNode = "/youtube-playlist/CSE3109/Mohsena_Ashraf_MAM"
    private val requestReference: DatabaseReference = database.getReference(databaseParentNode)

    fun loadData(allDataList: MutableLiveData<List<YouTubeVideoData>>) {

        requestReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val dataList: List<YouTubeVideoData> =
                        snapshot.children.mapNotNull { dataSnapshot ->
                            dataSnapshot.getValue(YouTubeVideoData::class.java)!!

                        }
                    allDataList.postValue(dataList)
                } catch (_: Exception) {
                    // Provide error message
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Provide cancel message
            }


        })

        requestReference.keepSynced(true)
    }
}
