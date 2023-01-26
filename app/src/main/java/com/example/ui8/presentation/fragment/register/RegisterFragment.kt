package com.example.ui8.presentation.fragment.register

import android.os.Bundle
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

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val accountRepository by lazy {
        AccountRepositoryImplementation(
            AccountDataBase.getDataBase(
                requireContext().applicationContext
            )
        )
    }
    private val addAccountToDatabaseUseCase by lazy { AddAccountToDatabaseUseCase(accountRepository) }

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

        }
    }
}
