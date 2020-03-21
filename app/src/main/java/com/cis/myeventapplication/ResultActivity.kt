package com.cis.myeventapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {


    var mAuth: FirebaseAuth? = null
    var mAuthListener:FirebaseAuth.AuthStateListener? = null
    private val TAG:String = "Result Activity"

    var information_nameevent:TextView? = null
    var information_detailevent:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser

        result_emaildata.text = user!!.email
//        result_uiddata.text = user!!.uid

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users =firebaseAuth.currentUser
            if (users == null){
                startActivity(Intent(this@ResultActivity,LoginActivity::class.java))
                finish()
            }
        }

        information_nameevent = findViewById(R.id.getname) as TextView
        information_detailevent = findViewById(R.id.getdetail) as EditText

        var intent = getIntent()

        information_nameevent!!.setText(""+intent.getStringExtra("Nameevent"))
        information_detailevent!!.setText(""+intent.getStringExtra("detailevent"))



        result_signOutBtn.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()
        }

        result_addeventbutton.setOnClickListener {
            val i = Intent(this,addeventActivity::class.java)
            startActivity(i)
            Toast.makeText(this,"บันทึกข้อมูลกิจกรรม",Toast.LENGTH_LONG).show()
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){moveTaskToBack(true)}
        return super.onKeyDown(keyCode, event)
    }
}
