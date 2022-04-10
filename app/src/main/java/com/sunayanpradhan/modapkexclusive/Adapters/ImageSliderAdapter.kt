package com.sunayanpradhan.modapkexclusive.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sunayanpradhan.modapkexclusive.Activities.AppDownloadActivity
import com.sunayanpradhan.modapkexclusive.Models.SlideModel
import com.sunayanpradhan.modapkexclusive.R

class ImageSliderAdapter(var list: ArrayList<SlideModel>,var context: Context):RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var isImage:ImageView=itemView.findViewById(R.id.is_image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view=LayoutInflater.from(context).inflate(R.layout.image_slider_design,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var currentItem=list[position]


        Picasso.get().load(currentItem.slideImage).into(holder.isImage)


        holder.itemView.setOnClickListener {


            var intent= Intent(context, AppDownloadActivity::class.java)
            intent.putExtra("appId",currentItem.appId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)


        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}