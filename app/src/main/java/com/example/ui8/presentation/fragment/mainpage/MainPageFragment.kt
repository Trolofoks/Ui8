package com.example.ui8.presentation.fragment.mainpage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ui8.R
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.databinding.FragmentMainPageBinding

class MainPageFragment : BaseFragment<FragmentMainPageBinding>(FragmentMainPageBinding::inflate) {
    private lateinit var controller: NavController
    private lateinit var vm: MainPageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()
        vm = ViewModelProvider(
            this,
            MainPageViewModelFactory(requireContext().applicationContext))
            .get(MainPageViewModel::class.java)

        vm.returnAccountLiveData.observe(viewLifecycleOwner, Observer { account->
            binding.apply {
                textName.text = account.name
                textEmail.text = account.email
                textNumber.text = account.number
            }
        })
        vm.getAccount()

        vm.returnLogOutLiveData.observe(viewLifecycleOwner, Observer {
            if (it) controller.navigate(R.id.loginFragment)
        })

        binding.button2.setOnClickListener {
            vm.logOut()
        }
    }
}