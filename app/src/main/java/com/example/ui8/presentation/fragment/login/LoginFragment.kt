package com.example.ui8.presentation.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.domain.model.NumberAndPassModel
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate){
    private val vm by viewModel<LoginViewModel>()
    private lateinit var controller: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()

        lifecycleScope.launchWhenCreated {
            vm.correctFlow.collect(){correct ->
                binding.apply {
                    if (correct){
                        controller.navigate(R.id.mainPageFragment)
                    } else {
                        outlinedTextFieldPassword.helperText = getString(R.string.wrong_login_data)
                    }
                }
            }
        }

        binding.buttonLogin.setOnClickListener {
            Log.d("MyLog","vm.getAccount")
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