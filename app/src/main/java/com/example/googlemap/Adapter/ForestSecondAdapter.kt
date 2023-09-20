package com.example.googlemap.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlemap.ForestRCVActivity
import com.example.googlemap.LakeRCVActivity
import com.example.googlemap.ModelClass.ForestModelClass
import com.example.googlemap.R

class ForestSecondAdapter(var context: ForestRCVActivity, var list: ArrayList<ForestModelClass>,var onitemclick:(String)->Unit):RecyclerView.Adapter<ForestSecondAdapter.MyViewHoder>() {
    class MyViewHoder(itemview: View):RecyclerView.ViewHolder(itemview) {

        var price: TextView = itemView.findViewById(R.id.price_item)
        var day: TextView = itemView.findViewById(R.id.day_item)
        var people: TextView = itemView.findViewById(R.id.people_item)
        var img_forest: ImageView = itemView.findViewById(R.id.img_forest)
        var book : Button = itemview.findViewById(R.id.Book_forest)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForestSecondAdapter.MyViewHoder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.forest_item_file, parent, false)
        return ForestSecondAdapter.MyViewHoder(view)    }

    override fun onBindViewHolder(holder: ForestSecondAdapter.MyViewHoder, position: Int) {
        holder.price.text = list[position].Price
        holder.day.text = list[position].Day
        holder.people.text = list[position].People

        Glide.with(context).load(list[position].ImageUri).into(holder.img_forest)

        holder.book.setOnClickListener {

            onitemclick.invoke(
                list[position].ImageUri


            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}