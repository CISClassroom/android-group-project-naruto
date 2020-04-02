package com.cis.myeventapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cis.myeventapplication.add_student_to_event.AddStuToEventActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {


    var mAuth: FirebaseAuth? = null //เช็คว่าตัวแปร mAuth นั้นมีค่าเท่ากับ null รึเปล่า
    var mAuthListener:FirebaseAuth.AuthStateListener? = null //รับข้อมูล user ที่ได้จากการsign-in  หากค่าเป็น null ก็แปลว่า user ยังไม่ได้ sign-in นั่นเอง
    private val TAG:String = "Result Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mAuth = FirebaseAuth.getInstance()//เช็คว่าตัวแปร mAuth นั้นมีค่าเท่ากับ null รึเปล่า
        val user = mAuth!!.currentUser //ตรวจสอบข้อมูลuserในfirebase

        result_emaildata.text = user!!.email //เอาข้อมูลuser มาโชว์ในtextview

        //รับข้อมูล user ที่ได้จากการsign-in โ หากค่าเป็น null ก็แปลว่า user ยังไม่ได้ sign-in นั่นเอง
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users =firebaseAuth.currentUser
            if (users == null){ //ถ้า user เป็นค่าว่าง ให้กลับไปที่หน้าล็อกอิน
                startActivity(Intent(this@ResultActivity,LoginActivity::class.java))
                finish()
            }
        }

        addStudentToEvent.setOnClickListener{
            startActivity(Intent(this@ResultActivity,AddStuToEventActivity::class.java))
        }

        result_addeventbutton.setOnClickListener {
            val i = Intent(this,AddeventActivity::class.java) //เก็บตัวแปร i สำหรับการข้ามไปอีกหน้า Activity นึง
            startActivity(i)
            Toast.makeText(this,"เพิ่มข้อมูลกิจกรรม",Toast.LENGTH_SHORT).show()
        }

        result_signOutBtn.setOnClickListener {
            mAuth!!.signOut() //ถ้ามีการล็อกเอ้าท์ ให้กลับไปที่หน้า MainActivity
            Toast.makeText(this,"ออกจากระบบ", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()
        }

        student_button.setOnClickListener {
            startActivity(Intent(this@ResultActivity,StudentActivity::class.java))
            Toast.makeText(this,"รายชื่อกิจกรรม",Toast.LENGTH_SHORT).show()
        }
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
