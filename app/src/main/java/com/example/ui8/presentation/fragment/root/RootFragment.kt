package com.example.ui8.presentation.fragment.root

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentRootBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootFragment : BaseFragment<FragmentRootBinding>(FragmentRootBinding::inflate) {
    private val vm by viewModel<RootViewModel>()
    private lateinit var controller: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()

        lifecycleScope.launchWhenCreated {
            vm.signed.collect(){signed ->
                if (signed){
                    controller.navigate(R.id.signedInFragment)
                } else {
                    controller.navigate(R.id.signedOutFragment)
                }
            }
        }
        vm.get()
    }
}