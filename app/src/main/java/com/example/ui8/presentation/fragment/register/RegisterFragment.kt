package com.example.ui8.presentation.fragment.register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val accountRepository by lazy {
        AccountRepositoryImplementation(
            AccountDataBase.getDataBase(
                requireContext().applicationContext
            )
        )
    }
    private val addAccountToDatabaseUseCase by lazy { AddAccountToDatabaseUseCase(accountRepository) }
    private val nameRulesDoneUseCase = NameRulesDoneUseCase()
    private val emailRulesDoneUseCase = EmailRulesDoneUseCase()
    private val numberRulesDoneUseCase = NumberRulesDoneUseCase()
    private val passwordRulesDoneUseCase = PasswordRulesDoneUseCase()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonRegister.setOnClickListener {
            val account = AccountMidModel(
                name = binding.textImputEditName.text.toString(),
                email = binding.textImputEditEmail.text.toString(),
                number = binding.textImputEditNumber.text.toString(),
                password = binding.textImputEditPassword.text.toString()
            )
            val get = addAccountToDatabaseUseCase.execute(account)
            Toast.makeText(requireContext(), "${get}", Toast.LENGTH_SHORT).show()

            val name = nameRulesDoneUseCase.execute(binding.textImputEditName.text.toString())
            val email = emailRulesDoneUseCase.execute(binding.textImputEditEmail.text.toString())
            val number = numberRulesDoneUseCase.execute(binding.textImputEditNumber.text.toString())
            val password = passwordRulesDoneUseCase.execute(binding.textImputEditPassword.text.toString())

            Log.d("MyLog","${name} ${email} ${number} ${password}")

        }
    }
}
