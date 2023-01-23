package com.example.ui8.fragment.signedin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ui8.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentSignedInBinding

class SignedInFragment : BaseFragment<FragmentSignedInBinding>(FragmentSignedInBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //сюда чтото в будущем засунуть можно
        findNavController().navigate(R.id.mainPageFragment)
    }

}