package com.example.ui8.fragment.onboard.item

import com.example.ui8.BaseFragment
import com.example.ui8.databinding.FragmentItem1Binding


class Item1Fragment : BaseFragment<FragmentItem1Binding>(FragmentItem1Binding::inflate) {
    companion object {
        @JvmStatic
        fun newInstance() = Item1Fragment()
    }
}