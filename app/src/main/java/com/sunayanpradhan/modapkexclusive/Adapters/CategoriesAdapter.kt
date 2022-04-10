package com.sunayanpradhan.modapkexclusive.Adapters

import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sunayanpradhan.modapkexclusive.Activities.AppDownloadActivity
import com.sunayanpradhan.modapkexclusive.Activities.ListActivity
import com.sunayanpradhan.modapkexclusive.Models.CategoriesModel
import com.sunayanpradhan.modapkexclusive.R

class CategoriesAdapter(var list: ArrayList<CategoriesModel>,var context: Context):RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var designLogo: ImageView =itemView.findViewById(R.id.design_logo)
        var designTitle: TextView =itemView.findViewById(R.id.design_title)
        var designDownload: ImageButton =itemView.findViewById(R.id.design_download)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= LayoutInflater.from(context).inflate(R.layout.app_design,parent,false)
        return CategoriesAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var currentItem=list[position]

        Picasso.get().load(currentItem.categoryLogo).into(holder.designLogo)

        holder.designTitle.text=currentItem.categoryName

        holder.designDownload.visibility=View.GONE

        holder.designTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,12f)

        holder.itemView.setOnClickListener {

            var intent= Intent(context, ListActivity::class.java)
            intent.putExtra("categoryId",currentItem.categoryId)
            intent.putExtra("categoryName",currentItem.categoryName)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}