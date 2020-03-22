package com.cis.myeventapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class EventAdapter(val mCtx: Context,val layoutResId:Int,val eventlist:List<Event>)
    :ArrayAdapter<Event>(mCtx,layoutResId,eventlist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       //return super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)
        var student: Event = eventlist[position]
        return  view
    }
}