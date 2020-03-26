package com.cis.myeventapplication.add_student_to_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cis.myeventapplication.Event
import com.cis.myeventapplication.R
import com.cis.myeventapplication.StudentEvent
import com.cis.myeventapplication.ToDo
import com.cis.myeventapplication.adapter.StudentDialogAdapter
import com.cis.myeventapplication.adapter.StudentListAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_stu_to_event.*
import kotlinx.android.synthetic.main.activity_add_stu_to_event.addevent_saveBtn
import kotlinx.android.synthetic.main.activity_add_stu_to_event.spinner
import kotlinx.android.synthetic.main.activity_addevent.*
import kotlinx.android.synthetic.main.activity_detail.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "NAME_SHADOWING")
class AddStuToEventActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    var toDoItemList: MutableList<ToDo>? = null
    private val event = ArrayList<String>()
    private val resStudent = ArrayList<String>()
    private var value01 = ""
    private lateinit var mStudentDialogAdapter: StudentDialogAdapter
    private lateinit var mStudentListAdapter: StudentListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_stu_to_event)
        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)
        toDoItemList = mutableListOf()
        onClick()
    }

    private var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            addDataToList(dataSnapshot.child("events"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }


    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        // Check if current database contains any collection
        // check if the collection has any to do items or not
        while (items.hasNext()) {
            // get current item
            val todo = ToDo.create()
            val currentItem = items.next()
            val map = currentItem.getValue() as HashMap<String, Any>
            // add data to object
            todo.name = map["nameEvent"] as String
            todo.object_id = map["event_id"] as String


            toDoItemList!!.add(todo)
        }

        //เพิ่มเข้า spinner


        for (t in toDoItemList!!) {
            event.add(t.name!!)
        }
        getValueSpinnerEvent()


    }

    private fun getValueSpinnerEvent() {
        val count = ArrayList<String>()
        val adapters = ArrayAdapter(this, android.R.layout.simple_spinner_item, event)
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = adapters

        spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {

                if (i != -1) {
                    tvEvn.text = adapterView.getItemAtPosition(i).toString()
                    value01 = adapterView.getItemAtPosition(i).toString()
                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        val v = resources.getStringArray(R.array.namestudent)
        val adapters02 = ArrayAdapter(this, android.R.layout.simple_spinner_item, v)
        adapters02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2?.adapter = adapters02

        spinner2!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {

                if (i != -1) {

                    resStudent.add(adapterView.getItemAtPosition(i).toString())

                    mStudentListAdapter = StudentListAdapter(applicationContext, resStudent)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(applicationContext)
                        adapter = mStudentListAdapter
                        mStudentListAdapter.notifyDataSetChanged()

                    }

                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }


    }

    private fun onClick() {

        val ref = FirebaseDatabase.getInstance().getReference("students event")
        val eventId = ref.push().key
        var eventsStudent: StudentEvent
        addevent_saveBtn.setOnClickListener {
            for (t in resStudent) {
                eventsStudent = eventId?.let { StudentEvent(value01, resStudent) }!!

                ref.child(eventId).setValue(eventsStudent).addOnCompleteListener {
                    Toast.makeText(applicationContext,"บันทึกกิจกรรมแล้ว", Toast.LENGTH_SHORT).show()
                }
            }
            finish()
        }

        imgBack_signature.setOnClickListener {
            finish()
        }

    }
}
