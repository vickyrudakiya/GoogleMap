package com.example.googlemap.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlemap.LakeRCVActivity
import com.example.googlemap.ModelClass.LakeModelClass
import com.example.googlemap.R

class LakeSecondAdapter(var context: LakeRCVActivity, var list: ArrayList<LakeModelClass>):RecyclerView.Adapter<LakeSecondAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

        var price: TextView = itemView.findViewById(R.id.price_item)
        var day: TextView = itemView.findViewById(R.id.day_item)
        var people: TextView = itemView.findViewById(R.id.people_item)

        var img_lake: ImageView = itemView.findViewById(R.id.img_lake)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LakeSecondAdapter.MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.lake_item_file, parent, false)
        return LakeSecondAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: LakeSecondAdapter.MyViewHolder, position: Int) {

        holder.price.text = list[position].Price
        holder.day.text = list[position].Day
        holder.people.text = list[position].People

        Glide.with(context).load(list[position].ImageUri).into(holder.img_lake)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}