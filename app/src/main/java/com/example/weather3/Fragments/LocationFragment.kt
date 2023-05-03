package com.example.weather3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.weather3.databinding.FragmentLocationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private val binding get()  = _binding!!

    private lateinit var auth: FirebaseAuth


    private lateinit var comm: Communicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val view = binding.root

        comm = requireActivity() as Communicator

        val colat = binding.latitudeBox
        val colong = binding.longitudeBox
        val cotimezone = binding.timezoneBox

//        binding.cancelButton.setOnClickListener {
//            comm.passData(colat.toString(), colong.toString(), cotimezone.toString())
//        }
//
////        val user = auth.currentUser
//        binding.SignOutButton.setOnClickListener {
//            Firebase.auth.signOut()

//            val action = LocationFragmentDirections.actionLocationFragmentToSignInFragment(user.)
//            findNavController().navigate(action)
//
//
//        }



//        binding.cancelButton.setOnClickListener {
//            val action = LocationFragmentDirections.actionLocationFragmentToWeatherDataFragment()
//            findNavController().navigate(action)
//        }

        return view
    }

}