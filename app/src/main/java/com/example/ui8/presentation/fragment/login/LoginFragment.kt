package com.example.ui8.presentation.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.domain.model.NumberAndPassModel
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate){
    private lateinit var vm: LoginViewModel
    private lateinit var controller: NavController
    var visible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()
        vm = ViewModelProvider(this, LoginViewModelFactory(requireContext().applicationContext))
            .get(LoginViewModel::class.java)

        vm.resultLiveList.observe(viewLifecycleOwner, Observer {
            binding.apply {
                if (it){
                    controller.navigate(R.id.mainPageFragment)
                } else {
                    outlinedTextFieldPassword.helperText = getString(R.string.wrong_login_data)
                }
            }
        })
        binding.buttonLogin.setOnClickListener {
            vm.getAccountByNameAndPass(nameAndPass = NumberAndPassModel(
                number = binding.textImputEditNumber.text.toString(),
                password = binding.textImputEditPassword.text.toString()
            ))
        }
        binding.linearSingUp.setOnClickListener {
            controller.navigate(R.id.registerFragment)
        }

    }


}