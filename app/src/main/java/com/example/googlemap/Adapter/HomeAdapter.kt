package com.example.googlemap.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlemap.Fragment.HomeFragment
import com.example.googlemap.ModelClass.ProfileModelClass
import com.example.googlemap.R

class HomeAdapter(var context: HomeFragment, var list:ArrayList<ProfileModelClass>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtPlace: TextView = itemView.findViewById(R.id.txtPlace)
        var img: ImageView = itemView.findViewById(R.id.img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item_file, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtPlace.text = list[position].place

        Glide.with(context).load(list[position].ImageUri).into(holder.img)



    }
}