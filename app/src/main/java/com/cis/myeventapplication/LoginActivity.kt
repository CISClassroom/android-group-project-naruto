package com.cis.myeventapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var mAuth:FirebaseAuth? = null //เครื่องหมาย? คือว่างได้
    private val TAG:String = "Login Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance() //เช็คว่าตัวแปร mAuth นั้นมีค่าเท่ากับ null รึเปล่า

        if (mAuth!!.currentUser != null){ //ตรวจสอบว่าถ้ามีการล็อกอินอยู่แล้วให้ไปหน้า resultActivity
            startActivity(Intent(this@LoginActivity,ResultActivity::class.java))
            finish()
        }

        login_loginBtn.setOnClickListener {
            val email = login_emaileditText.text.toString().trim{it <= ' '} //รับค่าจากpaintext มาเก็บไว้ในemail
            val password = login_passwordeditText2.text.toString().trim { it <= ' ' } //รับค่าจากpaintext มาเก็บไว้ในpassword

            if (email.isEmpty()){ //ถ้าemail ว่าง
                Toast.makeText(this,"Please enter your Email Address",Toast.LENGTH_LONG).show() //ให้toast บอกกรอกสิ
                Log.d(TAG,"Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()){ //ถ้าemail ว่าง
                Toast.makeText(this,"Please enter your Password",Toast.LENGTH_LONG).show() //ให้toast บอกกรอกสิ
                Log.d(TAG,"Password was empty!")
                return@setOnClickListener
            }
            mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener { 
                task ->
                if (!task.isSuccessful){
                    if (password.length < 6 ){
                        login_passwordeditText2.error = "Please check your password.Password must have minimum 6 characters"
                        Log.d(TAG,"Enter password less than 6 characters")
                    }else{
                        Toast.makeText(this,"Autentication Failed"+task.exception,Toast.LENGTH_SHORT).show()
                        Log.d(TAG,"Authentication Failed"+task.exception)
                    }
                }else{
                    Toast.makeText(this,"Sign in Successful",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity,ResultActivity::class.java))
                    finish()
                }
            }
        }

    }
}
