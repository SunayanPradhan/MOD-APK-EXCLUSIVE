package com.sunayanpradhan.modapkexclusive.Activities

import android.app.AlertDialog
import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.android.gms.ads.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.sunayanpradhan.modapkexclusive.Adapters.ScreenshotAdapter
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.Models.ScreenshotModel
import com.sunayanpradhan.modapkexclusive.R

class AppDownloadActivity : AppCompatActivity() {

    lateinit var aadLogo:ImageView
    lateinit var aadTitle:TextView
    lateinit var aadPublisher:TextView
    lateinit var aadVersion:TextView
    lateinit var aadSize:TextView
    lateinit var aadCategory:TextView
    lateinit var aadInstall:Button
    lateinit var aadScreenshots:ShimmerRecyclerView
    lateinit var aadDescription:TextView


    lateinit var list: ArrayList<ScreenshotModel>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_download)

        aadLogo=findViewById(R.id.aad_logo)
        aadTitle=findViewById(R.id.aad_title)
        aadPublisher=findViewById(R.id.aad_publisher)
        aadVersion=findViewById(R.id.aad_version)
        aadSize=findViewById(R.id.aad_size)
        aadCategory=findViewById(R.id.aad_category)
        aadInstall=findViewById(R.id.aad_install)
        aadScreenshots=findViewById(R.id.aad_screenshots)
        aadDescription=findViewById(R.id.aad_description)

        MobileAds.initialize(this)



        var intent= intent

        var appId= intent.getStringExtra("appId")

        FirebaseDatabase.getInstance().reference.child("apps").child(appId.toString()).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var data: AppModel? =snapshot.getValue(AppModel::class.java)

                Picasso.get().load(data?.logo).into(aadLogo)
                aadTitle.text=data?.title
                aadPublisher.text=data?.publisher
                aadVersion.text=data?.version
                aadSize.text=data?.appsize
                aadCategory.text=data?.category
                //install

                aadInstall.setOnClickListener {


                    val dialogView = View.inflate(this@AppDownloadActivity, R.layout.custom_dialog_layout, null)

                    val builder = AlertDialog.Builder(this@AppDownloadActivity).setView(dialogView).create()

                    builder.show()

                    builder.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    var dialogCancel = dialogView.findViewById(R.id.dialog_cancel) as ImageView
                    var dialogDownload=dialogView.findViewById(R.id.dialog_download) as Button
                    var dialogLogo=dialogView.findViewById(R.id.dialog_logo) as ImageView

                    Picasso.get().load(data?.logo).into(dialogLogo)

                    dialogCancel.setOnClickListener {

                        builder.dismiss()

                    }

                    dialogDownload.setOnClickListener {






                        try {
                            val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                            val uri = Uri.parse(data?.applink)
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)
                            Toast.makeText(this@AppDownloadActivity, "Download Started", Toast.LENGTH_SHORT)
                                .show()

                            builder.dismiss()

                        } catch (e: Exception) {
                            Toast.makeText(this@AppDownloadActivity, "Error: " + e.message, Toast.LENGTH_SHORT)
                                .show()
                            e.printStackTrace()
                        }

                        if (data?.obblink!=""){

                            try {
                                val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                                val uri = Uri.parse(data?.obblink)
                                val request = DownloadManager.Request(uri)
                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                downloadManager.enqueue(request)

                                builder.dismiss()

                            } catch (e: Exception) {
                                Toast.makeText(this@AppDownloadActivity, "Error: " + e.message, Toast.LENGTH_SHORT)
                                    .show()
                                e.printStackTrace()
                            }



                        }



                    }


                }





                aadDescription.text=data?.description


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        list= ArrayList()

        var adapter=ScreenshotAdapter(list,this)
        var layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        aadScreenshots.layoutManager=layoutManager


        FirebaseDatabase.getInstance().reference.child("apps").child(appId.toString()).child("screenshots").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){
                    var data=dataSnapshot.getValue(ScreenshotModel::class.java)
                    data?.screenshotId= dataSnapshot.key.toString()
                    data?.appId=appId.toString()
                    list.add(data!!)
                }
                adapter.notifyDataSetChanged()
                aadScreenshots.adapter=adapter

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AppDownloadActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        })



    }



}