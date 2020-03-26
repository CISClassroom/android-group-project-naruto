package com.cis.myeventapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cis.myeventapplication.R

class StudentDialogAdapter(context: Context, private val list: ArrayList<String>, private val res:(ArrayList<String>)->Unit): RecyclerView.Adapter<StudentDialogAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_student,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val array = ArrayList<String>()
        holder.tvName.text = list[position]

        holder.checkbox.setOnClickListener {view ->
            holder.checkbox.isChecked = (view as CheckBox).isChecked

            if(holder.checkbox.isChecked){
                array.add(list[position])
            }else{
                array.remove(list[position])
            }

            res.invoke(array)
        }






    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox: CheckBox = itemView.findViewById(R.id.checkbox)
        var tvName: TextView = itemView.findViewById(R.id.tvName)

    }
}