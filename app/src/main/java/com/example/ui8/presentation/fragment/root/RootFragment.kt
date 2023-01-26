package com.example.ui8.presentation.fragment.root

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentRootBinding

class RootFragment : BaseFragment<FragmentRootBinding>(FragmentRootBinding::inflate) {
    private lateinit var vm: RootViewModel
    private lateinit var controller: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(
            this,
            RootViewModelFactory(requireContext().applicationContext)
        ).get(RootViewModel::class.java)
        controller = findNavController()
        //проверка на регистрацию
        vm.resultLive.observe(viewLifecycleOwner, Observer{ signed->
            if (signed){
                controller.navigate(R.id.signedInFragment)
            } else {
                controller.navigate(R.id.signedOutFragment)
            }
        })
        vm.get()
    }
}