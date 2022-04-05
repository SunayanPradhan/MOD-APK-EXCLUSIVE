package com.sunayanpradhan.modapkexclusive.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunayanpradhan.modapkexclusive.Adapters.AppListAdapter
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R




class ListFragment : Fragment() {


    lateinit var listRecyclerView: RecyclerView

    lateinit var list:ArrayList<AppModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listRecyclerView=view.findViewById(R.id.list_recyclerView)
        list= ArrayList()

        var adapter= AppListAdapter(list,requireContext())
        var layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,true)
        listRecyclerView.layoutManager=layoutManager


        adapter.notifyDataSetChanged()
        listRecyclerView.adapter=adapter


        var bundle=this.arguments

        var data:String= bundle?.getString("key")!!



    }


}