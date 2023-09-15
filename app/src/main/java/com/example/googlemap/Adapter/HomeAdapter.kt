package com.example.googlemap.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlemap.HomeFragment
import com.example.googlemap.ModelClass.ProfileModelClass
import com.example.googlemap.R

class HomeAdapter(var Context: HomeFragment, var list:ArrayList<ProfileModelClass>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var place: EditText = itemView.findViewById(R.id.place_txt)
        var img: ImageView = itemView.findViewById(R.id.img_home_item)

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

        Glide.with(Context).load(list[position].ImageUri).into(holder.img);

    }
}