package com.example.ui8.presentation.fragment.numbercheck

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.domain.model.AccountMidModel
import com.example.ui8.R
import com.example.ui8.databinding.FragmentNumberCheckBinding
import com.example.ui8.presentation.BaseFragment
import com.example.domain.Constance
import com.example.ui8.presentation.SimpleTextWatcher
import org.koin.androidx.viewmodel.ext.android.viewModel

class NumberCheckFragment : BaseFragment<FragmentNumberCheckBinding>(FragmentNumberCheckBinding::inflate) {
    private lateinit var controller: NavController
    private val vm by viewModel<NumberCheckViewModel>()
    private lateinit var data: AccountMidModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()


        //можно сделать проверку, можно юзать deprecated делай что хочеш
        data = arguments?.getSerializable(Constance.TRANSFER_DATA_TO_NUMBER) as AccountMidModel

        lifecycleScope.launchWhenCreated {
            vm.correctDigitsFlow.collect(){correct ->
                if (correct){
                    controller.navigate(R.id.mainPageFragment)
                } else {
                    delete()
                }
            }
        }

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