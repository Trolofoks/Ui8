package com.example.ui8.fragment.root

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.data.repository.MainRepositoryImplementation
import com.example.data.storage.sharedPref.SharedPrefMainInfoStorage
import com.example.domain.usecase.GetMainUserInfoUseCase
import com.example.ui8.R
import com.example.ui8.databinding.FragmentRootBinding

class RootFragment : Fragment() {
    private lateinit var binding: FragmentRootBinding
    private lateinit var vm: RootViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRootBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this, RootViewModelFactory(requireContext().applicationContext))
            .get(RootViewModel::class.java)
        //проверка на регистрацию
        vm.resultLive.observe(viewLifecycleOwner, Observer{ signed->
            val controller = findNavController()
            if (signed){
                controller.navigate(R.id.signedInFragment)
            } else {
                controller.navigate(R.id.signedOutFragment)
            }
        })
        vm.get()



    }
}