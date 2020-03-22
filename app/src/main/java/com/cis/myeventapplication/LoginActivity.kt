package com.cis.myeventapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var mAuth:FirebaseAuth? = null
    private val TAG:String = "Login Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null){
            startActivity(Intent(this@LoginActivity,ResultActivity::class.java))
            finish()
        }

        login_loginBtn.setOnClickListener {
            val email = login_emaileditText.text.toString().trim{it <= ' '}
            val password = login_passwordeditText2.text.toString().trim { it <= ' ' }

            if (email.isEmpty()){
                Toast.makeText(this,"Please enter your Email Address",Toast.LENGTH_LONG).show()
                Log.d(TAG,"Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()){
                Toast.makeText(this,"Please enter your Password",Toast.LENGTH_LONG).show()
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
