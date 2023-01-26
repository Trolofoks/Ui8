package com.example.ui8.presentation.fragment.onboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.MainRepositoryImplementation
import com.example.data.storage.sharedPref.SharedPrefMainInfoStorage
import com.example.domain.usecase.SaveSeenOnBoardUseCase

class OnBoardViewModelFactory(context: Context): ViewModelProvider.Factory {
    private val mainRepository by lazy {
        MainRepositoryImplementation(
            mainInfoStorage = SharedPrefMainInfoStorage(
                context = context
            )
        )
    }
    private val saveSeenOnBoardUseCase by lazy { SaveSeenOnBoardUseCase(mainRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnBoardViewModel(saveSeenOnBoardUseCase) as T
    }
}