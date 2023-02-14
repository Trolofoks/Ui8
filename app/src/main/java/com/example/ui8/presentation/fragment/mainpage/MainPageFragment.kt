package com.example.ui8.presentation.fragment.mainpage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ui8.R
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.databinding.FragmentMainPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : BaseFragment<FragmentMainPageBinding>(FragmentMainPageBinding::inflate) {
    private lateinit var controller: NavController
    private val vm by viewModel<MainPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()

        lifecycleScope.launchWhenCreated {
            vm.accountDataFlow.collect(){account ->
                binding.apply {
                    textName.text = account.name
                    textEmail.text = account.email
                    textNumber.text = account.number
                }
            }
        }
        vm.getAccount()

        lifecycleScope.launchWhenCreated {
            vm.logOutFlow.collect(){loginOut ->
                if (loginOut) controller.navigate(R.id.loginFragment)
            }
        }

        binding.button2.setOnClickListener {
            vm.logOut()
        }
    }
}