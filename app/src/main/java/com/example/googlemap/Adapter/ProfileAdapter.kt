package com.example.googlemap.Adapter

import android.content.Context
import android.media.Image.Plane
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemap.ModelClass.ProfileModelClass
import com.example.googlemap.R

class ProfileAdapter(var Context: Context,var list:ArrayList<ProfileModelClass>,var onitemclick:(String,String,String,String,String,String,String,String,String)->Unit): RecyclerView.Adapter<ProfileAdapter.MyViewHolder>() {
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        var place : EditText = itemView.findViewById(R.id.edtPlace)
        var city : EditText = itemView.findViewById(R.id.edtcity)
        var day : EditText = itemView.findViewById(R.id.edtDay)
        var price : EditText = itemView.findViewById(R.id.edtprice)
        var email : EditText = itemView.findViewById(R.id.edtEmail)
        var rate : EditText = itemView.findViewById(R.id.edtRating)
        var phone : EditText = itemView.findViewById(R.id.edtphone)
        var description : EditText = itemView.findViewById(R.id.edtDescription)
        var submit : EditText = itemView.findViewById(R.id.Btn_Submit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_profile,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProfileAdapter.MyViewHolder, position: Int) {

         holder.place.setText(list[position].place.toString())
         holder.city.setText(list[position].city.toString())
         holder.day.setText(list[position].day.toString())
         holder.price.setText(list[position].price.toString())
         holder.email.setText(list[position].email.toString())
         holder.rate.setText(list[position].rate.toString())
         holder.phone.setText(list[position].phone.toString())
         holder.description.setText(list[position].description.toString())
         holder.submit.setOnClickListener {

         }
            onitemclick.invoke(
                list[position].place,
                list[position].city,
                list[position].day,
                list[position].price,
                list[position].rate,
                list[position].email,
                list[position].phone,
                list[position].description,
                list[position].key
            )


    }
}