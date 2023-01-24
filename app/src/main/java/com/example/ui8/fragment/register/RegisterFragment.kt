package com.example.ui8.fragment.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ui8.BaseFragment
import com.example.ui8.R
import com.example.ui8.databinding.FragmentRegisterBinding
import com.example.ui8.sqlite.AccountDataBase
import com.example.ui8.sqlite.AccountModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBase = AccountDataBase.getDataBase(requireContext())
        binding.buttonRegister.setOnClickListener {
            val account = AccountModel(
                id = null,
                name = binding.textImputEditName.text.toString(),
                email = binding.textImputEditEmail.text.toString(),
                number = binding.textImputEditNumber.text.toString(),
                password = binding.textImputEditPassword.text.toString()
            )
            Thread{
                dataBase.getDao().insertAccount(account)
            }
        }
    }
}
