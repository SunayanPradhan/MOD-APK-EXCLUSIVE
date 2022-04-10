package com.sunayanpradhan.modapkexclusive.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sunayanpradhan.modapkexclusive.Activities.ListActivity
import com.sunayanpradhan.modapkexclusive.Adapters.AppRecyclerAdapter
import com.sunayanpradhan.modapkexclusive.Adapters.CategoriesAdapter
import com.sunayanpradhan.modapkexclusive.Adapters.ImageSliderAdapter
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.Models.CategoriesModel
import com.sunayanpradhan.modapkexclusive.Models.SlideModel
import com.sunayanpradhan.modapkexclusive.R

class AppsFragment : Fragment() {

    lateinit var imageSlider:ShimmerRecyclerView

    lateinit var categoriesLayout:ConstraintLayout
    lateinit var categoryRecyclerView:ShimmerRecyclerView

    lateinit var trendingLayout:ConstraintLayout
    lateinit var trendingRecyclerView:ShimmerRecyclerView

    lateinit var releaseLayout:ConstraintLayout
    lateinit var releaseRecyclerView:ShimmerRecyclerView

    lateinit var recommendedLayout:ConstraintLayout
    lateinit var recommendedRecyclerView:ShimmerRecyclerView


    lateinit var categoryList:ArrayList<CategoriesModel>
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


        return inflater.inflate(R.layout.fragment_apps,container,false)
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



        var imageList = ArrayList<SlideModel>() // Create image list

        var sliderAdapter=ImageSliderAdapter(imageList,requireContext())
        var sliderLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        imageSlider.layoutManager=sliderLayoutManager



        FirebaseDatabase.getInstance().reference.child("slide").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for (dataSnapshot in snapshot.children){

                    var data:SlideModel?=dataSnapshot.getValue(SlideModel::class.java)

                    data?.slideId=dataSnapshot.key.toString()

                    if (data?.slideType=="app") {
                        imageList.add(data)
                    }

                }

                sliderAdapter.notifyDataSetChanged()

                imageSlider.adapter=sliderAdapter




            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })







        categoryList= ArrayList()
        var categoryAdapter=CategoriesAdapter(categoryList,requireContext())
        var categoryLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        categoryRecyclerView.layoutManager=categoryLayoutManager



        //data fetch

        FirebaseDatabase.getInstance().reference.child("appCategories").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){
                    var data=dataSnapshot.getValue(CategoriesModel::class.java)
                    data?.categoryId=dataSnapshot.key.toString()

                    if (data?.categoryType=="app") {
                        categoryList.add(data!!)
                    }

                }

                categoryAdapter.notifyDataSetChanged()
                categoryRecyclerView.adapter=categoryAdapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })





        trendingList= ArrayList()
        var trendingAdapter=AppRecyclerAdapter(trendingList, requireActivity())
        var trendingLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        trendingRecyclerView.layoutManager=trendingLayoutManager


        //data fetch
        FirebaseDatabase.getInstance().reference.child("apps").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){

                    var data=dataSnapshot.getValue(AppModel::class.java)

                    data?.appId= dataSnapshot.key.toString()

                    if (data?.trending==true && data?.appType=="app") {
                        trendingList.add(data)
                    }

                }

                trendingAdapter.notifyDataSetChanged()
                trendingRecyclerView.adapter=trendingAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })








        releaseList= ArrayList()
        var releaseAdapter=AppRecyclerAdapter(releaseList, requireActivity())
        var releaseLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        releaseRecyclerView.layoutManager=releaseLayoutManager


        //data fetch

        FirebaseDatabase.getInstance().reference.child("apps").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {

                        var data = dataSnapshot.getValue(AppModel::class.java)

                        data?.appId = dataSnapshot.key.toString()

                        if (data?.release == true && data?.appType == "app") {
                            releaseList.add(data)
                        }


                }

                releaseAdapter.notifyDataSetChanged()
                releaseRecyclerView.adapter = releaseAdapter

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })









        recommendedList= ArrayList()
        var recommendedAdapter=AppRecyclerAdapter(recommendedList, requireActivity())
        var recommendedLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recommendedRecyclerView.layoutManager=recommendedLayoutManager


        //data fetch



        FirebaseDatabase.getInstance().reference.child("apps").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){

                    var data=dataSnapshot.getValue(AppModel::class.java)

                    data?.appId= dataSnapshot.key.toString()

                    if (data?.recommended==true && data?.appType=="app") {



                            recommendedList.add(data!!)

                    }

                }


                recommendedAdapter.notifyDataSetChanged()
                recommendedRecyclerView.adapter=recommendedAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })



        trendingLayout.setOnClickListener {


            var intent=Intent(requireContext(),ListActivity::class.java)

            intent.putExtra("keyName","Trending apps")
            intent.putExtra("keyType","app")

            startActivity(intent)

        }


       releaseLayout.setOnClickListener {


            var intent=Intent(requireContext(),ListActivity::class.java)

            intent.putExtra("keyName","New release apps")
            intent.putExtra("keyType","app")

            startActivity(intent)

        }


        recommendedLayout.setOnClickListener {


            var intent=Intent(requireContext(),ListActivity::class.java)

            intent.putExtra("keyName","Recommended apps")
            intent.putExtra("keyType","app")

            startActivity(intent)

        }





    }

}