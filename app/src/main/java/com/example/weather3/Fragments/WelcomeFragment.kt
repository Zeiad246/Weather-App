package com.example.weather3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather3.R
import com.example.weather3.databinding.FragmentWeatherdataBinding


class WelcomeFragment : Fragment() {

    private var _binding: FragmentWeatherdataBinding? = null
    private val binding get()  = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherdataBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


}