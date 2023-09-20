package com.example.googlemap.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemap.ModelClass.BeachModelClass
import com.example.googlemap.ModelClass.MountianModelClass
import com.example.googlemap.R

class BeachAdapter(var context : Context, var list:ArrayList<BeachModelClass>,var onitemclick:(String,String,String,String,String)->Unit): RecyclerView.Adapter<BeachAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {


        var place : EditText =itemview.findViewById(R.id.edt_beach_place)
        var price : EditText =itemview.findViewById(R.id.edt_beach_price)
        var day   : EditText =itemview.findViewById(R.id.edt_beach_day)
        var people: EditText =itemview.findViewById(R.id.edt_beach_people)
        //        var img_beach:EditText=itemview.findViewById(R.id.Button_Add_Image_beach)
        var submit_beach: EditText =itemview.findViewById(R.id.Btn_Submit_beach)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_beach,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.place.setText(list[position].Beach.toString())
        holder.price.setText(list[position].Price.toString())
        holder.day.setText(list[position].Day.toString())
        holder.people.setText(list[position].People.toString())
//        holder.img_beach.setText(list[position].Beach.toString())
        holder.submit_beach.setOnClickListener {
        }
        onitemclick.invoke(
            list[position].Beach,
            list[position].Price,
            list[position].Day,
            list[position].People,
            list[position].key

        )
    }

}

