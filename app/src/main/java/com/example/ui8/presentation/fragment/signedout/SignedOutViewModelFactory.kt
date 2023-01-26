package com.example.ui8.presentation.fragment.signedout

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.MainRepositoryImplementation
import com.example.data.storage.sharedPref.SharedPrefMainInfoStorage
import com.example.domain.usecase.GetMainUserInfoUseCase

class SignedOutViewModelFactory(context: Context): ViewModelProvider.Factory {
    private val mainRepository by lazy {
        MainRepositoryImplementation(
            mainInfoStorage = SharedPrefMainInfoStorage(
                context = context
            )
        )
    }
    private val getMainUserInfoUseCase by lazy { GetMainUserInfoUseCase(mainRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignedOutViewModel(getMainUserInfoUseCase) as T
    }
}