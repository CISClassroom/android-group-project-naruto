package com.cis.myeventapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.*

class EventActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var eventlist : MutableList<Event>
    lateinit var listView : ListView
//    lateinit var arrayList: ArrayList<String>
//    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        eventlist = mutableListOf()
        listView = findViewById(R.id.listview1)
        ref = FirebaseDatabase.getInstance().getReference("events")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    eventlist.clear()
                    for (e in p0.children){
                        val event = e.getValue(Event::class.java)
                        eventlist.add(event!!)
                    }
                    val adapter = EventAdapter(this@EventActivity,R.layout.activity_event,eventlist)
                    listView.adapter = adapter
                }
            }
        })
//        listView = findViewById(R.id.listviewtxt)
//         arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1)
//        listView.setAdapter(arrayAdapter)
    }
}

