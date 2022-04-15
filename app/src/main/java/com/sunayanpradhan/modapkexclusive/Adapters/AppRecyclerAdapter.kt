package com.sunayanpradhan.modapkexclusive.Adapters

import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.squareup.picasso.Picasso
import com.sunayanpradhan.modapkexclusive.Activities.AppDownloadActivity
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R

class AppRecyclerAdapter(var list: ArrayList<AppModel>, var context: Context):RecyclerView.Adapter<AppRecyclerAdapter.ViewHolder>() {

    private var mRewardedAd: RewardedAd? = null
    private final var TAG = "AppRecyclerAdapter"

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        var designLogo:ImageView=itemView.findViewById(R.id.design_logo)
        var designTitle:TextView=itemView.findViewById(R.id.design_title)
        var designDownload:ImageButton=itemView.findViewById(R.id.design_download)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.app_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var currentItem=list[position]

        mRewardedAd!=null

        Picasso.get().load(currentItem.logo).into(holder.designLogo)

        holder.designTitle.text=currentItem.title


        holder.itemView.setOnClickListener {

            var intent= Intent(context, AppDownloadActivity::class.java)
            intent.putExtra("appId",currentItem.appId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

        holder.designDownload.setOnClickListener {



            val dialogView = View.inflate(context, R.layout.custom_dialog_layout, null)

            val builder = AlertDialog.Builder(context).setView(dialogView).create()

            builder.show()

            builder.window?.setBackgroundDrawableResource(android.R.color.transparent)

            var dialogCancel = dialogView.findViewById(R.id.dialog_cancel) as ImageView
            var dialogDownload=dialogView.findViewById(R.id.dialog_download) as Button
            var dialogLogo=dialogView.findViewById(R.id.dialog_logo) as ImageView

            Picasso.get().load(currentItem?.logo).into(dialogLogo)


            dialogCancel.setOnClickListener {

                builder.dismiss()

            }

            dialogDownload.setOnClickListener {


                try {
                    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val uri = Uri.parse(currentItem?.applink)
                    val request = DownloadManager.Request(uri)
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    downloadManager.enqueue(request)
                    Toast.makeText(context, "Download Started", Toast.LENGTH_SHORT)
                        .show()

                    builder.dismiss()

                } catch (e: Exception) {
                    Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }

                if (currentItem?.obblink!=""){

                    try {
                        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        val uri = Uri.parse(currentItem?.obblink)
                        val request = DownloadManager.Request(uri)
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        downloadManager.enqueue(request)

                        builder.dismiss()

                    } catch (e: Exception) {
                        Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_SHORT)
                            .show()
                        e.printStackTrace()
                    }



                }




            }




        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}