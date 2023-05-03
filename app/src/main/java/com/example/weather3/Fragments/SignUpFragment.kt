package com.example.weather3.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.weather3.MainActivity
import com.example.weather3.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get()  = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.SignUpButton.setOnClickListener {

//            SignUp()

        }

        return view
    }

//    fun SignUp(){
//
//        val email = binding.EmailBox.text.toString()
//        val password = binding.PasswordBox.text.toString()
//        val confirmPassword = binding.confirmPasswordBox.text.toString()
//
//        if (email.isNotEmpty()){
//            if (password == confirmPassword && password.isNotEmpty()) {
//                auth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(activity as MainActivity) { task ->
//                        if(task.isSuccessful) {
//                            Log.d(TAG, "MESSAGE: user created successfully")
//                            val user = auth.currentUser
//
//                            if (user != null){
//                                Toast.makeText(context,"Sign Up Successful", Toast.LENGTH_SHORT).show()
//
//                                val action =
//                                    SignUpFragmentDirections.actionSignUpFragToWeatherDataFrag(user.email!!)
//                                findNavController().navigate(action)
//                            }
//                        } else {
//                            Log.w(TAG, "MESSAGE: sign up failure", task.exception)
//                            Toast.makeText(context, "Failed to create account", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//            } else {
//                Toast.makeText(context, "Please make sure you passwords match", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Toast.makeText(context, "Please enter an email", Toast.LENGTH_SHORT).show()
//        }
//
//
//
//
//    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}