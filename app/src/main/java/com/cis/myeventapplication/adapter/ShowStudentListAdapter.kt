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

class ShowStudentListAdapter(context: Context, private val list: ArrayList<String>): RecyclerView.Adapter<ShowStudentListAdapter.ViewHolder>()  {

    val array = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_student,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.count.text = (position+1).toString()
        holder.name.text = list[position]

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var count: TextView = itemView.findViewById(R.id.count)
        var name: TextView = itemView.findViewById(R.id.name)

    }
}