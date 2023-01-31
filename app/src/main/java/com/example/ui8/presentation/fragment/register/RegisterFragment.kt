package com.example.ui8.presentation.fragment.register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.databinding.FragmentRegisterBinding
import com.example.data.accountstorage.sqlite.AccountDataBase
import com.example.data.accountstorage.model.AccountModel
import com.example.data.repository.AccountRepositoryImplementation
import com.example.domain.model.AccountMidModel
import com.example.domain.repository.AccountRepository
import com.example.domain.usecase.AddAccountToDatabaseUseCase
import com.example.domain.usecase.rules.EmailRulesDoneUseCase
import com.example.domain.usecase.rules.NameRulesDoneUseCase
import com.example.domain.usecase.rules.NumberRulesDoneUseCase
import com.example.domain.usecase.rules.PasswordRulesDoneUseCase
import com.example.ui8.R
import com.example.ui8.presentation.fragment.signedout.SignedOutViewModelFactory

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private lateinit var vm : RegisterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this,
        RegisterViewModelFactory(requireContext().applicationContext)
        ).get(RegisterViewModel::class.java)

        vm.resultLiveDataCorrectList.observe(viewLifecycleOwner, Observer {checkErrorList ->
            binding.apply {
                if (checkErrorList[0]){
                    outlinedTextFieldName.helperText = null
                } else{
                    binding.textImputEditName.setText("")
                    outlinedTextFieldName.helperText = getString(R.string.reg_name_error)
                }
                if (checkErrorList[1]){
                    outlinedTextFieldEmail.helperText = null
                } else {
                    binding.textImputEditEmail.setText("")
                    outlinedTextFieldEmail.helperText = getString(R.string.reg_email_error)
                }
                if (checkErrorList[2]){
                    outlinedTextFieldNumber.helperText = null
                } else {
                    binding.textImputEditNumber.setText("")
                    outlinedTextFieldNumber.helperText = getString(R.string.reg_number_error)
                }
                if (checkErrorList[3]){
                    outlinedTextFieldPassword.helperText = null
                } else {
                    binding.textImputEditPassword.setText("")
                    outlinedTextFieldPassword.helperText = getString(R.string.reg_password_error)
                }
            }

        })

        binding.buttonRegister.setOnClickListener {
            val accList = listOf<String>(
                binding.textImputEditName.text.toString(),
                binding.textImputEditEmail.text.toString(),
                binding.textImputEditNumber.text.toString(),
                binding.textImputEditPassword.text.toString()
            )
            vm.addAccount(accList)
        }
    }
}
