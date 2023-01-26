package com.example.ui8.presentation.fragment.signedin

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentSignedInBinding

class SignedInFragment : BaseFragment<FragmentSignedInBinding>(FragmentSignedInBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //сюда чтото в будущем засунуть можно
        findNavController().navigate(R.id.mainPageFragment)
    }

}