package com.cis.myeventapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class addeventActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)

       // val mRootRef = FirebaseDatabase.getInstance().reference //รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        //firebaseauth = FirebaseAuth.getInstance()

    }
}
