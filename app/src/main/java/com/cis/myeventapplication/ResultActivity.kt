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


    lateinit var information_nameevent:TextView
    lateinit var information_detailevent:TextView

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

        information_nameevent = findViewById(R.id.getname) as TextView
        information_detailevent = findViewById(R.id.getdetail) as TextView

        var intent = getIntent()

//        information_nameevent!!.setText(""+intent.getStringExtra("Nameevent"))
//        information_detailevent!!.setText(""+intent.getStringExtra("detailevent"))


        val rootRef = FirebaseDatabase.getInstance().reference
        val tripsRef = rootRef.child("events")

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list: MutableList<String?> = ArrayList()
                for (ds in dataSnapshot.children) {
                    val nameEvents =
                        ds.child("nameEvent").getValue(String::class.java)
                    val detail =
                        ds.child("detailEvent").getValue(String::class.java)
                    Log.d("TAG", "$nameEvents / $detail")

                    information_nameevent.setText(nameEvents)
                    information_detailevent.setText(detail)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        tripsRef.addListenerForSingleValueEvent(valueEventListener)


        result_addeventbutton.setOnClickListener {
            val i = Intent(this,addeventActivity::class.java)
            startActivity(i)
            Toast.makeText(this,"บันทึกข้อมูลกิจกรรม",Toast.LENGTH_LONG).show()
        }

        result_signOutBtn.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()
        }

        student_button.setOnClickListener {
            startActivity(Intent(this@ResultActivity,StudentActivity::class.java))
            Toast.makeText(this,"รายชื่อนักศึกษาชั้นปีที่ 3 สาขาวิทยกาคอมพิวเตอร์และสารสนเทศ",Toast.LENGTH_LONG).show()
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
