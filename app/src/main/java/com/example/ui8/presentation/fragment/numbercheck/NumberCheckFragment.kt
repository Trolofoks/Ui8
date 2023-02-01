package com.example.ui8.presentation.fragment.numbercheck

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.domain.model.AccountMidModel
import com.example.ui8.R
import com.example.ui8.databinding.FragmentNumberCheckBinding
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.presentation.Constance
import com.example.ui8.presentation.SimpleTextWatcher

class NumberCheckFragment : BaseFragment<FragmentNumberCheckBinding>(FragmentNumberCheckBinding::inflate) {
    private lateinit var controller: NavController
    private lateinit var vm: NumberCheckViewModel
    private lateinit var data: AccountMidModel
    //можно сделать проверку, можно юзать deprecated делай что хочеш
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()

        vm = ViewModelProvider(
            this,
            NumberCheckViewModelFactory(requireContext().applicationContext)
        ).get(NumberCheckViewModel::class.java)

        data = arguments?.getSerializable(Constance.TRANSFER_DATA_TO_NUMBER, AccountMidModel::class.java)!!


        vm.resultDigitsIsCorrectLive.observe(viewLifecycleOwner, Observer{ correct->
            if (correct){
                controller.navigate(R.id.mainPageFragment)
            } else {
                delete()
            }
        })

        binding.apply {
            box1.addTextChangedListener(object : SimpleTextWatcher() {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) box2.requestFocus()
                    if (s?.length == 4) {
                        digitsIsCorrect(box1.text.toString())
                        Toast.makeText(requireContext(), "pasted", Toast.LENGTH_SHORT).show()
                        box1.text.clear()
                    }
                }
            })

            // Set Text Change Listener for EditText2
            box2.addTextChangedListener(object : SimpleTextWatcher() {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) box3.requestFocus()
                }
            })

            // Set Text Change Listener for EditText3
            box3.addTextChangedListener(object : SimpleTextWatcher() {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) box4.requestFocus()

                }
            })
            box4.addTextChangedListener(object : SimpleTextWatcher(){
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) {
                        val digits: String = box1.text.toString() +
                                box2.text.toString() +
                                box3.text.toString() +
                                box4.text.toString()
                        digitsIsCorrect(digits)
                    } else if (s?.isEmpty() == true && before == 1) delete()
                }
            })

        }
    }
    private fun digitsIsCorrect(digits: String) {
        vm.save(digitPass = digits, account = data)
    }
    private fun delete(){
        binding.apply {
            box1.text.clear()
            box2.text.clear()
            box3.text.clear()
            box4.text.clear()
            box1.requestFocus()
        }
    }
}