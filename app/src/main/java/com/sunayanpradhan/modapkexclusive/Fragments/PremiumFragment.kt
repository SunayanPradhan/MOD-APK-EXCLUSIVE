package com.sunayanpradhan.modapkexclusive.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sunayanpradhan.modapkexclusive.Activities.ListActivity
import com.sunayanpradhan.modapkexclusive.Adapters.AppRecyclerAdapter
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R


class PremiumFragment : Fragment() {

    lateinit var premiumAppLayout:ConstraintLayout
    lateinit var premiumAppRecyclerView:ShimmerRecyclerView
    lateinit var premiumGameLayout: ConstraintLayout
    lateinit var premiumGameRecyclerView: ShimmerRecyclerView
    lateinit var appList:ArrayList<AppModel>
    lateinit var gameList:ArrayList<AppModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_premium, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        premiumAppLayout=view.findViewById(R.id.premium_app_layout)
        premiumAppRecyclerView=view.findViewById(R.id.premium_app_recyclerView)
        premiumGameLayout=view.findViewById(R.id.premium_game_layout)
        premiumGameRecyclerView=view.findViewById(R.id.premium_game_recyclerView)


        premiumAppLayout.setOnClickListener {


            var intent= Intent(requireContext(), ListActivity::class.java)

            intent.putExtra("keyName","Premium apps")
            intent.putExtra("keyType","app")

            startActivity(intent)

        }

        premiumGameLayout.setOnClickListener {


            var intent= Intent(requireContext(), ListActivity::class.java)

            intent.putExtra("keyName","Premium games")
            intent.putExtra("keyType","game")

            startActivity(intent)

        }



        appList= ArrayList()
        var appAdapter= AppRecyclerAdapter(appList, requireActivity())
        var appLayoutManager=GridLayoutManager(context,3)
        premiumAppRecyclerView.layoutManager=appLayoutManager


        //data fetch



        FirebaseDatabase.getInstance().reference.child("apps").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){

                    var data=dataSnapshot.getValue(AppModel::class.java)

                    data?.appId= dataSnapshot.key.toString()

                    if (data?.premium==true && data?.appType=="app") {



                        appList.add(data!!)

                    }

                }


                appAdapter.notifyDataSetChanged()
                premiumAppRecyclerView.adapter=appAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })


        gameList= ArrayList()
        var gameAdapter= AppRecyclerAdapter(gameList, requireActivity())
        var gameLayoutManager=GridLayoutManager(context,3)
        premiumGameRecyclerView.layoutManager=gameLayoutManager


        //data fetch



        FirebaseDatabase.getInstance().reference.child("apps").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){

                    var data=dataSnapshot.getValue(AppModel::class.java)

                    data?.appId= dataSnapshot.key.toString()

                    if (data?.premium==true && data?.appType=="game") {



                        gameList.add(data!!)

                    }

                }


                gameAdapter.notifyDataSetChanged()
                premiumGameRecyclerView.adapter=gameAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })





    }


}