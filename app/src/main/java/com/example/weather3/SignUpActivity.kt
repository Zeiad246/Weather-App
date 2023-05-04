package com.example.weather3

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.weather3.Fragments.SignUpFragmentDirections
import com.example.weather3.databinding.ActivityMainBinding
import com.example.weather3.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivitySignUpBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mainBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        auth = Firebase.auth

        mainBinding.signUpButton2.setOnClickListener {
            SignUp()
        }
    }

    fun SignUp(){

        val email = mainBinding.emailBox2.text.toString()
        val password = mainBinding.passwordBox2.text.toString()
        val confirmPassword = mainBinding.confirmPasswordBox2.text.toString()

        if (email.isNotEmpty()){
            if (password == confirmPassword && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            Log.d(ContentValues.TAG, "MESSAGE: user created successfully")
                            val user = auth.currentUser

                            if (user != null){
                                Toast.makeText(applicationContext,"Sign Up Successful", Toast.LENGTH_SHORT).show()

                                val intent = Intent(applicationContext, LocationActivity::class.java)
                                startActivity(intent)

                            }
                        } else {
                            Log.w(ContentValues.TAG, "MESSAGE: sign up failure", task.exception)
                            Toast.makeText(applicationContext, "Failed to create account", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(applicationContext, "Please make sure you passwords match", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Please enter an email", Toast.LENGTH_SHORT).show()
        }




    }
}