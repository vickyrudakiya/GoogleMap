package com.example.googlemap.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlemap.BeachRCVActivity
import com.example.googlemap.ModelClass.BeachModelClass
import com.example.googlemap.R

class BeachSecondAdapter(var context: BeachRCVActivity, var list: ArrayList<BeachModelClass>) :
    RecyclerView.Adapter<BeachSecondAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var price: TextView = itemView.findViewById(R.id.price_item)
        var day: TextView = itemView.findViewById(R.id.day_item)
        var people: TextView = itemView.findViewById(R.id.people_item)
        var img_beach: ImageView = itemView.findViewById(R.id.img_beach)

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): BeachSecondAdapter.MyViewHolder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.beach_item_file, parent, false)
        return BeachSecondAdapter.MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BeachSecondAdapter.MyViewHolder, position: Int) {
        holder.price.text = list[position].Price
        holder.day.text = list[position].Day
        holder.people.text = list[position].People

        Glide.with(context).load(list[position].ImageUri).into(holder.img_beach)

    }
}
