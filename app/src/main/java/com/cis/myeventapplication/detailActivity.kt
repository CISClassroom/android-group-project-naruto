package com.cis.myeventapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class detailActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)
    }
    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("events"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message

        }
    }
    fun addDataToList(dataSnapshot: DataSnapshot) {
        val id = getIntent().getExtras()!!.getString("id")
        val items = dataSnapshot.children.iterator()
        while (items.hasNext()) {
            val currentItem = items.next().getValue() as HashMap<String, Any>
            if (currentItem.get("event_id")==id) {
                getname.text = currentItem.get("nameEvent") as String
                detail.text = currentItem.get("detailEvent") as String
                typeofevent.text = currentItem.get("typeEvent") as String
                amountofevent.text = currentItem.get("amount") as String
                eventStart.text = currentItem.get("dateofstart") as String
                eventEnd.text = currentItem.get("dateofend") as String
            }

        }
//        }
    }
}
