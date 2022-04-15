package com.sunayanpradhan.modapkexclusive.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.WindowManager.LayoutParams.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sunayanpradhan.modapkexclusive.Adapters.AppListAdapter
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R

class SearchActivity : AppCompatActivity() {

    lateinit var appSearch:AutoCompleteTextView
    lateinit var searchButton:ImageView
    lateinit var rvApps:RecyclerView

    lateinit var list:ArrayList<AppModel>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        appSearch=findViewById(R.id.app_search)
        searchButton=findViewById(R.id.search_button)
        rvApps=findViewById(R.id.rvApps)
        list= ArrayList()

        window.setSoftInputMode(SOFT_INPUT_STATE_VISIBLE)

        showKeyboard(appSearch)

        appSearch.setOnEditorActionListener { p0, p1, p2 ->
            var s: String = appSearch.text.toString().trim()

            if (p1==EditorInfo.IME_ACTION_DONE){

                hideKeyboard(appSearch)

            }

            false
        }


        var titleList:ArrayList<String> = ArrayList()


        FirebaseDatabase.getInstance().reference.child("apps").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){

                    var data:AppModel?=dataSnapshot.getValue(AppModel::class.java)
                    data?.appId=dataSnapshot.key.toString()
                    titleList.add(data?.title!!)

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        val titleAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, titleList)


        appSearch.setAdapter(titleAdapter)



        var adapter = AppListAdapter(list, this)
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvApps.layoutManager = layoutManager


        appSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {



            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {



            }

            override fun afterTextChanged(p0: Editable?) {

                FirebaseDatabase.getInstance().reference.child("apps").orderByChild("title").startAt(appSearch.text.toString()).endAt(appSearch.text.toString()+"\uf8ff")
                    .addListenerForSingleValueEvent(object :ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {

                            list.clear()

                            for (dataSnapshot in snapshot.children){

                                var data=dataSnapshot.getValue(AppModel::class.java)
                                data?.appId=dataSnapshot.key.toString()

                                list.add(data!!)

                            }

                            adapter.notifyDataSetChanged()

                            rvApps.adapter=adapter

                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })



            }


        })

        searchButton.setOnClickListener {

            FirebaseDatabase.getInstance().reference.child("apps").orderByChild("title").startAt(appSearch.text.toString()).endAt(appSearch.text.toString()+"\uf8ff")
                .addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {

                        list.clear()

                        for (dataSnapshot in snapshot.children){

                            var data=dataSnapshot.getValue(AppModel::class.java)
                            data?.appId=dataSnapshot.key.toString()

                            list.add(data!!)

                        }

                        adapter.notifyDataSetChanged()

                        rvApps.adapter=adapter

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })


        }


        appSearch.setOnDismissListener {

            var inputMethodManager:InputMethodManager= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.applicationWindowToken,0)

        }


    }

    private fun hideKeyboard(appSearch: AutoCompleteTextView?) {

        var manager= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        manager.hideSoftInputFromWindow(appSearch?.applicationWindowToken,0)


    }

    private fun showKeyboard(appSearch: AutoCompleteTextView?) {

        var manager= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        manager.showSoftInput(appSearch?.rootView,InputMethodManager.SHOW_IMPLICIT)

        appSearch?.requestFocus()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }

}