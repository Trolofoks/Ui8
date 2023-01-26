package com.example.ui8.presentation.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate){
    private lateinit var vm: LoginViewModel
    var visible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this, LoginViewModelFactory(requireContext().applicationContext))
            .get(LoginViewModel::class.java)
        vm.resultLiveList.observe(viewLifecycleOwner, Observer {
            Log.d("MyLog", "$it")
        })
        binding.buttonLogin.setOnClickListener {
            vm.getAllAccounts()
        }
        binding.textButtonSingUp.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

    }


}