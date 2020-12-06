package com.example.approvetest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.approvetest.Repository

class AppViewModelProviderFactory(
    val repository: Repository
) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(repository) as T
    }
}