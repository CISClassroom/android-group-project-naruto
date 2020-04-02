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


class AddeventActivity : AppCompatActivity() {

    private  val TAG:String = "addevent Activity"

    lateinit var addevent_name: EditText
    lateinit var addevent_detail:EditText
    lateinit var  addevent_save: Button
    lateinit var spinner: Spinner
    lateinit var amounteditText: EditText
    lateinit var dateStart: EditText
    lateinit var dateEnd: EditText

    var valueofspinner = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)

        addevent_name = findViewById(R.id.addevent_nameEditText3) as EditText
        addevent_detail = findViewById(R.id.addevent_detailEditText4) as EditText
        addevent_save = findViewById(R.id.addevent_saveBtn) as Button
        spinner = findViewById(R.id.spinner) as Spinner
        amounteditText = findViewById(R.id.amounteditText) as EditText
        dateStart = findViewById(R.id.dateStart) as EditText
        dateEnd = findViewById(R.id.dateEnd) as EditText

        val t = resources.getStringArray(R.array.credit)
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,t)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
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
           intent.putExtra("Nameevent",""+ addevent_name.text.toString())
            intent.putExtra("detailevent",""+addevent_detail.text.toString())
            intent.putExtra("amount",""+amounteditText.text.toString())
            intent.putExtra("dateStart",""+dateStart.text.toString())
            intent.putExtra("dateEnd",""+dateEnd.text.toString())

            if(addevent_name.text.isNotEmpty()
                && addevent_detail.text.isNotEmpty()
                && amounteditText.text.isNotEmpty()
                && dateStart.text.isNotEmpty()
                && dateEnd.text.isNotEmpty()){
                startActivity(intent)
            }
        }

    }
    private fun saveEvent(){
        val nameEvent = addevent_name.text.toString().trim()
        if (nameEvent.isEmpty()){
            //addevent_nameEditText3.error = "Please enter a name of Event"
            Toast.makeText(this,"กรุณาใส่ชื่อกิจกรรม",Toast.LENGTH_SHORT).show()
            return
        }


        val detailEvent = addevent_detailEditText4.text.toString()
        if (detailEvent.isEmpty()){
            //addevent_detail.error = "Please enter a description of Event"
            Toast.makeText(this,"กรุณาใส่รายละเอียดกิจกรมม",Toast.LENGTH_SHORT).show()
            return
        }

      val typeEvent =  valueofspinner.toString()
        if (typeEvent.isEmpty()){
            Toast.makeText(this,"กรุณาเลือกด้านกิจกรรม",Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amounteditText.text.toString()
        if (amount.isEmpty()){
            Toast.makeText(this,"กรุณากรอกจำนวนหน่วยกิต",Toast.LENGTH_SHORT).show()
            return

        }


        val dateStart = dateStart.text.toString()
        if (dateStart.isEmpty()){
            Toast.makeText(this,"กรุณากรอกวันที่เริ่มกิจกรรม",Toast.LENGTH_SHORT).show()
            return
        }

        val dateEnd = dateEnd.text.toString()
        if (dateEnd.isEmpty()){
            Toast.makeText(this,"กรุณากรอกวันสิ้นสุดกิจกรรม",Toast.LENGTH_SHORT).show()
            return
        }


        val ref = FirebaseDatabase.getInstance().getReference("events")

        val event_id = ref.push().key

        val events_student = event_id?.let { Event(it,nameEvent, detailEvent,typeEvent,amount,dateStart,dateEnd)}

        if (event_id != null) {
            ref.child(event_id).setValue(events_student).addOnCompleteListener {
                Toast.makeText(applicationContext,"บันทึกกิจกรรมแล้ว",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
