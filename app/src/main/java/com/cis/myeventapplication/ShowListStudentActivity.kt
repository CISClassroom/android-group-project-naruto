package com.cis.myeventapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_show_list_student.*

class ShowListStudentActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list_student)
        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)
    }
    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("students event"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message

        }

        fun addDataToList(dataSnapshot: DataSnapshot) {
            val id = getIntent().getExtras()!!.getString("id")
            val items = dataSnapshot.children.iterator()
            while (items.hasNext()) {
                val currentItem = items.next().getValue() as HashMap<String, Any>
//                if (currentItem.get("event_name") == id) {
                    textView9.text = currentItem.get("event_name") as String
                    textView15.text = currentItem.get("student_name") as String
//                }
            }
        }
    }
}
