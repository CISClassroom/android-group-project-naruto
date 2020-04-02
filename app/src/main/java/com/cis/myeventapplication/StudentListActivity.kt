package com.cis.myeventapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.cis.myeventapplication.adapter.ShowStudentListAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    private lateinit var mShowStudentListAdapter: ShowStudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        mDatabase = FirebaseDatabase.getInstance().reference //reference การดึงข้อมูลมาทั้งหมด getreference เลือกมา1 table
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener) //สั่งให้ m มันทำงาน ตอนที่เรากด แยกฟังก์ชันด้วย itemlistener

    }

    private var itemListener: ValueEventListener = object : ValueEventListener { //เดึงดาต้าเบส

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("students event"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        val list = ArrayList<String>()

        val event = intent.extras!!.getString("event") //เลือกกิจกรรม
        // Check if current database contains any collection
        // check if the collection has any to do items or not
        while (items.hasNext()) {
            // get current item

            val currentItem = items.next()
            val map = currentItem.value as HashMap<*, *>

            if( map["event_name"] as String == event){ // เช็คว่าที่เราเลือกตรงกับฐานข้อมูลไหม
                for (t in map["student_name"] as ArrayList<*>){ //t = ค่าที่อยู่ใน student name
                    list.add(t as String)//แอดข้อมูลชื่อนักศึกษาเข้าไปในlist
                }
            }

            // add data to object
        }
        mShowStudentListAdapter = ShowStudentListAdapter(this, list) //เอารายชื่อนักศึกษาไปเก็บไว้ในadapter

        rvStudent.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mShowStudentListAdapter
            mShowStudentListAdapter.notifyDataSetChanged()
        }

        Log.d("ASd8a1d", list.size.toString())
      //  adapter.notifyDataSetChanged()
    }
}
