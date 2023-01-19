package com.example.ui8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ui8.databinding.FragmentRootBinding

class RootFragment : Fragment() {
    private val binding by lazy { FragmentRootBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //проверка на регистрацию
        if (true){
            findNavController().navigate(R.id.signedInFragment)
        } else {
            findNavController().navigate(R.id.signedOutFragment)
        }
    }
}