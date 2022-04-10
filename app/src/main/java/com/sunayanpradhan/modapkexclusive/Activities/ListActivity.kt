package com.sunayanpradhan.modapkexclusive.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sunayanpradhan.modapkexclusive.Adapters.AppListAdapter
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R

class ListActivity : AppCompatActivity() {

    lateinit var listSearchTxt : TextView
    lateinit var listSearch:ImageView

    lateinit var listRecyclerView: RecyclerView

    lateinit var list:ArrayList<AppModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listSearchTxt=findViewById(R.id.list_search_txt)
        listSearch=findViewById(R.id.list_search)
        listRecyclerView=findViewById(R.id.list_recyclerView)
        list= ArrayList()

        var intent=intent

        var keyName=intent.getStringExtra("keyName")
        var keyType=intent.getStringExtra("keyType")

        var categoryId=intent.getStringExtra("categoryId")
        var categoryName=intent.getStringExtra("categoryName")




        if (keyName!=null &&keyType!=null && keyName=="Trending apps" && keyType=="app") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.trending==true && data?.appType=="app" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }

        if (keyName!=null &&keyType!=null && keyName=="Recommended apps" && keyType=="app") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.recommended==true && data?.appType=="app" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }

        if (keyName!=null &&keyType!=null && keyName=="New release apps" && keyType=="app") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.release==true && data?.appType=="app" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }



        if (keyName!=null &&keyType!=null && keyName=="Trending games" && keyType=="game") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.trending==true && data?.appType=="game" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }

        if (keyName!=null &&keyType!=null && keyName=="Recommended games" && keyType=="game") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.recommended==true && data?.appType=="game" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }

        if (keyName!=null &&keyType!=null && keyName=="New release games" && keyType=="game") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.release==true && data?.appType=="game" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }



        if (keyName!=null && keyType!=null && keyName=="Premium apps" && keyType=="app") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.premium==true && data?.appType=="app" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }





        if (keyName!=null && keyType!=null && keyName=="Premium games" && keyType=="game") {


            listSearchTxt.text = keyName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)


                            data?.appId = dataSnapshot.key.toString()

                            if (data?.premium==true && data?.appType=="game" ) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }






        if (categoryId!=null){

            listSearchTxt.text = categoryName.toString()

            var adapter = AppListAdapter(list, this)
            var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listRecyclerView.layoutManager = layoutManager

            FirebaseDatabase.getInstance().reference.child("apps")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {

                            var data = dataSnapshot.getValue(AppModel::class.java)

                            data?.appId = dataSnapshot.key.toString()

                            if (data?.category==categoryName) {

                                list.add(data!!)
                            }


                        }

                        adapter.notifyDataSetChanged()
                        listRecyclerView.adapter = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })


        }



    }
}