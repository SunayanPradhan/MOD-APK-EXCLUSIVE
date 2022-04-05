package com.sunayanpradhan.modapkexclusive.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sunayanpradhan.modapkexclusive.Activities.AppDownloadActivity
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R

class AppRecyclerAdapter(var list: ArrayList<AppModel>, var context: Context):RecyclerView.Adapter<AppRecyclerAdapter.ViewHolder>() {
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

        Picasso.get().load(currentItem.logo).into(holder.designLogo)

        holder.designTitle.text=currentItem.title

        holder.itemView.setOnClickListener {

            var intent= Intent(context, AppDownloadActivity::class.java)
            intent.putExtra("appId",currentItem.appId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

        holder.designDownload.setOnClickListener {

            Toast.makeText(context, "pressed", Toast.LENGTH_SHORT).show()

        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}