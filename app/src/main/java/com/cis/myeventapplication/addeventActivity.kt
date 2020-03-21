package com.cis.myeventapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.synthetic.main.activity_addevent.*


class addeventActivity : AppCompatActivity() {

    private  val TAG:String = "addevent Activity"

    var addevent_name: EditText? = null
    var addevent_detail:EditText? = null
    var addevent_save: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)



        addevent_name = findViewById(R.id.addevent_nameEditText3) as EditText
        addevent_detail = findViewById(R.id.addevent_detailEditText4) as EditText
        addevent_save = findViewById(R.id.addevent_saveBtn) as Button

        addevent_saveBtn.setOnClickListener {
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("Nameevent",""+addevent_name!!.getText().toString())
            intent.putExtra("detailevent",""+addevent_detail!!.getText().toString())
            startActivity(intent)
        }
    }

}
