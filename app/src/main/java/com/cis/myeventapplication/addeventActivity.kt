package com.cis.myeventapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_addevent.*


class addeventActivity : AppCompatActivity() {

    private  val TAG:String = "addevent Activity"

    lateinit var addevent_name: EditText
    lateinit var addevent_detail:EditText
    lateinit var  addevent_save: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)

        addevent_name = findViewById(R.id.addevent_nameEditText3) as EditText
        addevent_detail = findViewById(R.id.addevent_detailEditText4) as EditText
        addevent_save = findViewById(R.id.addevent_saveBtn) as Button

        addevent_saveBtn.setOnClickListener {

            saveEvent()
           val intent = Intent(this,ResultActivity::class.java)
           intent.putExtra("Nameevent",""+addevent_name!!.getText().toString())
            intent.putExtra("detailevent",""+addevent_detail!!.getText().toString())

            if(addevent_name.text.isNotEmpty() && addevent_detail.text.isNotEmpty()){
                startActivity(intent)
            }
        }

    }
    private fun saveEvent(){
        val nameEvent = addevent_name.text.toString().trim()
        if (nameEvent.isEmpty()){
            addevent_nameEditText3.error = "Please enter a name of Event"
            Toast.makeText(this,"Please enter a name of Event",Toast.LENGTH_LONG).show()
            Log.d(TAG,"Password was empty!")
            return
        }

        val detailEvent = addevent_detailEditText4.text.toString()
        if (detailEvent.isEmpty()){
            //ฝaddevent_detail.error = "Please enter a description of Event"

            Toast.makeText(this,"Please enter a description of Event",Toast.LENGTH_LONG).show()
            Log.d(TAG,"Password was empty!")
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("events")

        val event_id = ref.push().key

        val events_student = event_id?.let { Event(it,nameEvent, detailEvent) }

        if (event_id != null) {
            ref.child(event_id).setValue(events_student).addOnCompleteListener {
                Toast.makeText(applicationContext,"Event save successful",Toast.LENGTH_LONG).show()
            }
        }
    }

}
