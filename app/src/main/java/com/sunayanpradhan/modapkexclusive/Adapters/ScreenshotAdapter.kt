package com.sunayanpradhan.modapkexclusive.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sunayanpradhan.modapkexclusive.Models.ScreenshotModel
import com.sunayanpradhan.modapkexclusive.R

class ScreenshotAdapter(var list: ArrayList<ScreenshotModel>,var context: Context):RecyclerView.Adapter<ScreenshotAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var designScreenshot:ImageView=itemView.findViewById(R.id.design_screenshot)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.screenshot_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem=list[position]

        Picasso.get().load(currentItem.screenshot).into(holder.designScreenshot)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}