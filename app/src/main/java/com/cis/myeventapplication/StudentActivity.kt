package com.cis.myeventapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StudentActivity : AppCompatActivity() {

    lateinit var listview : ListView
    private val TAG:String = "Student Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)


        listview = findViewById(R.id.listview)
        val event = arrayOf("comseeit","comcamp")
        listview.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,event)

        //listview.setOnItemClickListener{parent: AdapterView<*>?, view: View?, position: Int, id: Long ->  }

    }
}
