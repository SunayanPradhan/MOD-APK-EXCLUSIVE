package com.sunayanpradhan.modapkexclusive.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sunayanpradhan.modapkexclusive.Adapters.AppRecyclerAdapter
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R

class AppsFragment : Fragment() {

    lateinit var imageSlider:ImageSlider

    lateinit var categoriesLayout:ConstraintLayout
    lateinit var categoryRecyclerView:ShimmerRecyclerView

    lateinit var trendingLayout:ConstraintLayout
    lateinit var trendingRecyclerView:ShimmerRecyclerView

    lateinit var releaseLayout:ConstraintLayout
    lateinit var releaseRecyclerView:ShimmerRecyclerView

    lateinit var recommendedLayout:ConstraintLayout
    lateinit var recommendedRecyclerView:ShimmerRecyclerView


    lateinit var trendingList:ArrayList<AppModel>
    lateinit var releaseList:ArrayList<AppModel>
    lateinit var recommendedList:ArrayList<AppModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v=inflater.inflate(R.layout.fragment_apps,container,false)


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageSlider=view.findViewById(R.id.image_slider)

        categoriesLayout=view.findViewById(R.id.categories_layout)
        categoryRecyclerView=view.findViewById(R.id.category_recyclerView)

        trendingLayout=view.findViewById(R.id.trending_layout)
        trendingRecyclerView=view.findViewById(R.id.trending_recyclerView)

        releaseLayout=view.findViewById(R.id.release_layout)
        releaseRecyclerView=view.findViewById(R.id.release_recyclerView)

        recommendedLayout=view.findViewById(R.id.recommended_layout)
        recommendedRecyclerView=view.findViewById(R.id.recommended_recyclerView)



        val imageList = ArrayList<SlideModel>() // Create image list



        imageList.add(SlideModel("https://cdn.unenvironment.org/2022-03/field-ge4d2466da_1920.jpg"))
        imageList.add(SlideModel("https://cdn.unenvironment.org/2022-03/field-ge4d2466da_1920.jpg"))
        imageList.add(SlideModel("https://cdn.unenvironment.org/2022-03/field-ge4d2466da_1920.jpg"))


        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)


        trendingList= ArrayList()
        var trendingAdapter=AppRecyclerAdapter(trendingList, requireActivity())
        var trendingLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
        trendingRecyclerView.layoutManager=trendingLayoutManager


        //data fetch

        trendingAdapter.notifyDataSetChanged()
        trendingRecyclerView.adapter=trendingAdapter



        releaseList= ArrayList()
        var releaseAdapter=AppRecyclerAdapter(releaseList, requireActivity())
        var releaseLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
        releaseRecyclerView.layoutManager=releaseLayoutManager


        //data fetch




        releaseAdapter.notifyDataSetChanged()
        releaseRecyclerView.adapter=releaseAdapter




        recommendedList= ArrayList()
        var recommendedAdapter=AppRecyclerAdapter(recommendedList, requireActivity())
        var recommendedLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
        recommendedRecyclerView.layoutManager=recommendedLayoutManager


        //data fetch



        FirebaseDatabase.getInstance().reference.child("apps").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){

                    var data=dataSnapshot.getValue(AppModel::class.java)

                    data?.appId= dataSnapshot.key.toString()

                    recommendedList.add(data!!)

                }

                recommendedAdapter.notifyDataSetChanged()
                recommendedRecyclerView.adapter=recommendedAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }

        })







        recommendedLayout.setOnClickListener {

            var bundle=Bundle()
            bundle.putString("key","Recommended apps")

            var listFragment=ListFragment()

            listFragment.arguments=bundle

            fragmentManager?.beginTransaction()?.replace(R.id.fragments,listFragment)?.commit()

        }





    }

}