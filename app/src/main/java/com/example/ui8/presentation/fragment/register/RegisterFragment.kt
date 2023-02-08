package com.example.ui8.presentation.fragment.register

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ui8.presentation.BaseFragment
import com.example.ui8.databinding.FragmentRegisterBinding
import com.example.ui8.R
import com.example.domain.Constance
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private lateinit var controller: NavController
    private val vm by viewModel<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = findNavController()


        binding.toolbar.setNavigationOnClickListener {
            controller.navigateUp()
        }
        //[0]-name, [1]-email, [2]-number, [3]-password
        vm.resultLiveDataCorrectList.observe(viewLifecycleOwner, Observer {checkErrorList ->
            binding.apply {
                outlinedTextFieldName.helperText = checkErrorList[0]
                outlinedTextFieldEmail.helperText = checkErrorList[1]
                outlinedTextFieldNumber.helperText = checkErrorList[2]
                outlinedTextFieldPassword.helperText = checkErrorList[3]
            }
        })

        vm.resultToSaveAccountLiveData.observe(viewLifecycleOwner,Observer{
            val bundle = Bundle()
            bundle.putSerializable(Constance.TRANSFER_DATA_TO_NUMBER, it)
            controller.navigate(R.id.numberCheckFragment, bundle)
        })

        binding.buttonRegister.setOnClickListener {
            val accList = arrayOf<String>(
                binding.textImputEditName.text.toString(),
                binding.textImputEditEmail.text.toString(),
                binding.textImputEditNumber.text.toString(),
                binding.textImputEditPassword.text.toString()
            )
            vm.addAccount(accList)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                controller.navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
