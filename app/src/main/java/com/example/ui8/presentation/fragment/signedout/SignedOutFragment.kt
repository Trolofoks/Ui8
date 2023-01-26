package com.example.ui8.presentation.fragment.signedout

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentSignedOutBinding

class SignedOutFragment : BaseFragment<FragmentSignedOutBinding>(FragmentSignedOutBinding::inflate) {
    private lateinit var vm: SignedOutViewModel
    private lateinit var controller: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this,
            SignedOutViewModelFactory(requireContext().applicationContext)
        ).get(SignedOutViewModel::class.java)
        controller = findNavController()
        //OnBoard
        vm.resultLive.observe(viewLifecycleOwner, Observer { seenOnBoard ->
            if (seenOnBoard){
                controller.navigate(R.id.loginFragment)
            } else{
                controller.navigate(R.id.onBoardFragment)
            }
        })
        vm.get()
    }
}