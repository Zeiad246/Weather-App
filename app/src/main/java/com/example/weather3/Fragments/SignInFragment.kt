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
import com.example.weather3.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get()  = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root


        auth = Firebase.auth


//        user?.let {
//            val name = it.displayName
//            val email = it.email
//            val photoUrl = it.photoUrl
//            val emailVerified = it.isEmailVerified
//            val uid = it.uid
//        }


        Log.d(TAG,"worksassssssss")
        binding.SignInButton.setOnClickListener {
            SignIn()
        }



        binding.SignUpButton.setOnClickListener {
           // signUpCaller()
        }

        return view
    }


    fun SignIn(){

        val email = binding.EmailBox.text.toString()
        val password = binding.PasswordBox.text.toString()

        if (email.isNotEmpty()){
            if (password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity as MainActivity) { task ->
                        if(task.isSuccessful) {
                            Log.d(TAG, "MESSAGE: sign in successful")
                            Toast.makeText(context,"Sign In Successful", Toast.LENGTH_SHORT).show()
                            val user = auth.currentUser

                            if (user != null){
                                val action = SignInFragmentDirections.actionSignInFragToWeatherDataFrag(user.email!!)
                                findNavController().navigate(action)

                            }
                        } else {
                            Log.w(TAG, "MESSAGE: sign in failure", task.exception)
                            Toast.makeText(context, "Sign In Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please enter email", Toast.LENGTH_SHORT).show()
        }
    }

//    fun signUpCaller(){
//        val user = auth.currentUser!!
//        val action = SignInFragmentDirections.(user.email!!)
//        findNavController().navigate(action)
//    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}