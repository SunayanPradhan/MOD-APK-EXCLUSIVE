package com.sunayanpradhan.modapkexclusive.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sunayanpradhan.modapkexclusive.Models.AppModel
import com.sunayanpradhan.modapkexclusive.R

class AppListAdapter(var list: ArrayList<AppModel>,var context:Context):RecyclerView.Adapter<AppListAdapter.ViewHolder>() {


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var designListLogo:ImageView=itemView.findViewById(R.id.design_list_logo)
        var designListTitle:TextView=itemView.findViewById(R.id.design_list_title)
        var designListCategory:TextView=itemView.findViewById(R.id.design_list_category)
        var designListAppsize:TextView=itemView.findViewById(R.id.design_list_appsize)
        var designListDownload:ImageButton=itemView.findViewById(R.id.design_list_download)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.app_list_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem=list[position]

        Picasso.get().load(currentItem.logo).into(holder.designListLogo)
        holder.designListTitle.text=currentItem.title
        holder.designListCategory.text=currentItem.category
        holder.designListAppsize.text=currentItem.appsize



    }

    override fun getItemCount(): Int {
        return list.size
    }
}