package com.example.ui8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class SignedOutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signed_out, container, false)
        //OnBoard
        if (false){
            //findNavController().navigate(R.)
        } else{
            findNavController().navigate(R.id.onBoardFragment)
        }
    }
}