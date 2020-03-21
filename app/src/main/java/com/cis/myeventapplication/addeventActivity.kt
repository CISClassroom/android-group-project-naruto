package com.cis.myeventapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class addeventActivity : AppCompatActivity() {

    private  val TAG:String = "addevent Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)

       // val mRootRef = FirebaseDatabase.getInstance().reference //รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        //firebaseauth = FirebaseAuth.getInstance()

        // Write a message to the database

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
                Toast.makeText(this@addeventActivity,"Hello",Toast.LENGTH_LONG).show()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(this@addeventActivity,"Failed database",Toast.LENGTH_LONG).show()
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
}
