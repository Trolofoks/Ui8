package com.example.ui8.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ui8.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate){
    private lateinit var vm: LoginViewModel
    var visible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this, LoginViewModelFactory(requireContext().applicationContext))
            .get(LoginViewModel::class.java)
        vm.resultLiveCheckPass.observe(viewLifecycleOwner, Observer {
        })
        binding.buttonLogin.setOnClickListener {
            visible = !visible
            binding.outlinedTextFieldPassword
            Toast.makeText(requireContext(), "$visible", Toast.LENGTH_SHORT).show()

        }

    }


}