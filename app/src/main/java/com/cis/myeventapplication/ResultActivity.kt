package com.cis.myeventapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {


    var mAuth: FirebaseAuth? = null
    var mAuthListener:FirebaseAuth.AuthStateListener? = null
    private val TAG:String = "Result Activity"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser



        result_emaildata.text = user!!.email

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users =firebaseAuth.currentUser
            if (users == null){
                startActivity(Intent(this@ResultActivity,LoginActivity::class.java))
                finish()
            }
        }




        var intent = getIntent()

//        information_nameevent!!.setText(""+intent.getStringExtra("Nameevent"))
//        information_detailevent!!.setText(""+intent.getStringExtra("detailevent"))





        result_addeventbutton.setOnClickListener {
            val i = Intent(this,addeventActivity::class.java)
            startActivity(i)
            Toast.makeText(this,"เพิ่มข้อมูลกิจกรรม",Toast.LENGTH_SHORT).show()
        }

        result_signOutBtn.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"ออกจากระบบ", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()
        }

        student_button.setOnClickListener {
            startActivity(Intent(this@ResultActivity,StudentActivity::class.java))
            Toast.makeText(this,"รายชื่อกิจกรรม",Toast.LENGTH_SHORT).show()
        }

//        button.setOnClickListener {
//            startActivity(Intent(this@ResultActivity,EventActivity::class.java))
//            finish()
//        }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener { mAuthListener }
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null){ mAuth!!.removeAuthStateListener { mAuthListener }}
    }

}
