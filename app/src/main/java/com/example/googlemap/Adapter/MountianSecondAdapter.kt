package com.example.googlemap.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlemap.ModelClass.MountianModelClass
import com.example.googlemap.MountianActivity
import com.example.googlemap.R

class MountianSecondAdapter(var context: MountianActivity, var list: ArrayList<MountianModelClass>) :
    RecyclerView.Adapter<MountianSecondAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var price: TextView = itemView.findViewById(R.id.price_item)
        var day: TextView = itemView.findViewById(R.id.day_item)
        var people: TextView = itemView.findViewById(R.id.people_item)

        var img_mountain: ImageView = itemView.findViewById(R.id.img_mountain)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MountianSecondAdapter.MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.mountain_item_file, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MountianSecondAdapter.MyViewHolder, position: Int) {

        holder.price.text = list[position].Price
        holder.day.text = list[position].Day
        holder.people.text = list[position].People

        Glide.with(context).load(list[position].ImageUri).into(holder.img_mountain)

    }
}


