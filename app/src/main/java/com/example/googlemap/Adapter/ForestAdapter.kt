package com.example.googlemap.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemap.ModelClass.ForestModelClass
import com.example.googlemap.R
import com.google.firebase.database.core.Context

class ForestAdapter(
    var list: ArrayList<ForestModelClass>,
    var context: Context,
    var onitemclick: (String, String, String, String, String) -> Unit) : RecyclerView.Adapter<ForestAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        var place: EditText = itemview.findViewById(R.id.edt_Forest_place)
        var price: EditText = itemview.findViewById(R.id.edt_Forest_price)
        var day: EditText = itemview.findViewById(R.id.edt_Forest_day)
        var people: EditText = itemview.findViewById(R.id.edt_Forest_people)

        //        var img_beach:EditText=itemview.findViewById(R.id.Button_Add_Image_beach)
        var submit_lake: EditText = itemview.findViewById(R.id.Btn_Submit_Forest)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForestAdapter.MyViewHolder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.forest_item_file, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ForestAdapter.MyViewHolder, position: Int) {
        holder.place.setText(list[position].Forest.toString())
        holder.price.setText(list[position].Price.toString())
        holder.day.setText(list[position].Day.toString())
        holder.people.setText(list[position].People.toString())
//        holder.img_beach.setText(list[position].Beach.toString())
        holder.submit_lake.setOnClickListener {
        }
        onitemclick.invoke(
            list[position].Forest,
            list[position].Price,
            list[position].Day,
            list[position].People,
            list[position].key
        )
    }

}

