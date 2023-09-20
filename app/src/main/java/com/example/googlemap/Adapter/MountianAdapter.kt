package com.example.googlemap.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemap.ModelClass.MountianModelClass
import com.example.googlemap.R

class MountianAdapter(var Context: Context, var list:ArrayList<MountianModelClass>, var onitemclick:(String, String, String, String, String)->Unit) : RecyclerView.Adapter<MountianAdapter.MyViewHolder>() {
    class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {

            var place :EditText=itemview.findViewById(R.id.edt_mountain_place)
            var price :EditText=itemview.findViewById(R.id.edt_mountain_price)
            var day   :EditText=itemview.findViewById(R.id.edt_mountain_day)
            var people:EditText=itemview.findViewById(R.id.edt_beach_people)
    //        var img_beach:EditText=itemview.findViewById(R.id.Button_Add_Image_beach)
            var submit_mountain:EditText=itemview.findViewById(R.id.Btn_Submit_mountain)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountianAdapter.MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_mountian2,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MountianAdapter.MyViewHolder, position: Int) {

        holder.place.setText(list[position].Mountian.toString())
        holder.price.setText(list[position].Price.toString())
        holder.day.setText(list[position].Day.toString())
        holder.people.setText(list[position].People.toString())
//        holder.img_beach.setText(list[position].Beach.toString())
        holder.submit_mountain.setOnClickListener {
        }
        onitemclick.invoke(
            list[position].Mountian,
            list[position].Price,
            list[position].Day,
            list[position].People,
            list[position].key

        )
    }


    override fun getItemCount(): Int {
        return list.size

    }

}