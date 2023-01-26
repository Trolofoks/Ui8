package com.example.ui8.presentation.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.R
import com.example.ui8.presentation.adapter.ViewPagerOnBoardAdapter
import com.example.ui8.databinding.FragmentOnBoardBinding
import com.example.ui8.presentation.fragment.onboard.item.Item1Fragment
import com.example.ui8.presentation.fragment.onboard.item.Item2Fragment
import com.example.ui8.presentation.fragment.onboard.item.Item3Fragment

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate) {

    private lateinit var vm: OnBoardViewModel
    private val fragmentList = listOf<Fragment>(
        Item1Fragment.newInstance(),
        Item2Fragment.newInstance(),
        Item3Fragment.newInstance()
    )
    private var counterPos: Int = 0
    private lateinit var controller: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()

        vm = ViewModelProvider(this, OnBoardViewModelFactory(requireContext().applicationContext))
            .get(OnBoardViewModel::class.java)

        vm.resultLivePos.observe(viewLifecycleOwner, Observer{ pos->
            binding.viewPager.currentItem = pos
        })

        binding.button.setOnClickListener {
            counterPos = binding.viewPager.currentItem
            counterPos++
            if (counterPos == fragmentList.size) {
                vm.save(seen = true)
                controller.navigate(R.id.loginFragment)
            } else {
                vm.savePos(counterPos)
            }
        }

        val adapter = ViewPagerOnBoardAdapter(activity as FragmentActivity, fragmentList)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }
}