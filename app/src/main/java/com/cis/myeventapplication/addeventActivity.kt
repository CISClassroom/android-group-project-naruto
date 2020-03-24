package com.cis.myeventapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_addevent.*


class addeventActivity : AppCompatActivity() {

    private  val TAG:String = "addevent Activity"

    lateinit var addevent_name: EditText
    lateinit var addevent_detail:EditText
    lateinit var  addevent_save: Button
    lateinit var spinner: Spinner
    var valueofspinner = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)

        addevent_name = findViewById(R.id.addevent_nameEditText3) as EditText
        addevent_detail = findViewById(R.id.addevent_detailEditText4) as EditText
        addevent_save = findViewById(R.id.addevent_saveBtn) as Button
        spinner = findViewById(R.id.spinner) as Spinner

        val t = resources.getStringArray(R.array.credit)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,t)
        spinner?.adapter = adapter

        spinner!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                    valueofspinner = adapterView.getItemAtPosition(i).toString()
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        addevent_saveBtn.setOnClickListener {
            saveEvent()

           val intent = Intent(this,ResultActivity::class.java)
           intent.putExtra("Nameevent",""+addevent_name!!.getText().toString())
            intent.putExtra("detailevent",""+addevent_detail!!.getText().toString())


            if(addevent_name.text.isNotEmpty() && addevent_detail.text.isNotEmpty() ){
                startActivity(intent)
            }
        }

    }
    private fun saveEvent(){
        val nameEvent = addevent_name.text.toString().trim()
        if (nameEvent.isEmpty()){
            addevent_nameEditText3.error = "Please enter a name of Event"
            Toast.makeText(this,"Please enter a name of Event",Toast.LENGTH_LONG).show()
            return
        }


        val detailEvent = addevent_detailEditText4.text.toString()
        if (detailEvent.isEmpty()){
            //ฝaddevent_detail.error = "Please enter a description of Event"
            Toast.makeText(this,"Please enter a description of Event",Toast.LENGTH_LONG).show()
            return
        }

      val typeEvent =  valueofspinner.toString()
        if (typeEvent.isEmpty()){
            Toast.makeText(this,"Please choose a type of Event",Toast.LENGTH_LONG).show()
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("events")

        val event_id = ref.push().key

        val events_student = event_id?.let { Event(it,nameEvent, detailEvent,typeEvent)}

        if (event_id != null) {
            ref.child(event_id).setValue(events_student).addOnCompleteListener {
                Toast.makeText(applicationContext,"Event save successful",Toast.LENGTH_LONG).show()
            }
        }
    }

}
