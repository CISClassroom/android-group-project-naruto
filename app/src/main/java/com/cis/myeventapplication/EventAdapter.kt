package com.cis.myeventapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class EventAdapter(val mCtx: Context,val layoutResId:Int,val eventlist:List<Event>)
    :ArrayAdapter<Event>(mCtx,layoutResId,eventlist) {

}


class ToDoItemAdapter(context: Context, toDoItemList: MutableList<ToDo>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoItemList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // create object from view
        val name: String = itemList.get(position).name as String

        val view: View
        val vh: ListRowHolder

        // get list view
        if (convertView == null) {
            view = mInflater.inflate(R.layout.iten_events, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        // add text to view
        vh.label.text = name

        //add button listenner

        return view
    }

    override fun getItem(index: Int): Any {
        return itemList.get(index)
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

    private class ListRowHolder(row: View?) {
        val label: TextView = row!!.findViewById<TextView>(R.id.textView2) as TextView
    }
}