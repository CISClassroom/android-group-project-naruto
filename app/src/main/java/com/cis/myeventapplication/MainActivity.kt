package com.cis.myeventapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mAuth:FirebaseAuth? = null //เครื่องหมาย? คือว่างได้ !! ตัวแปรนี้ไม่เป็นค่า null นะ
    private  val TAG:String = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance() //

        if (mAuth!!.currentUser != null){ //ตรวจสอบว่ามีการล็อกอินอยู่แล้วให้ไปหน้า resultActivity
            Log.d(TAG,"Continue with : " + mAuth!!.currentUser!!.email)
            //Toast.makeText(this,"continue ",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity,ResultActivity::class.java))
            finish()
        }

        main_emailBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
        }
    }
}
