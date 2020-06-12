package com.sdv.attractgrouptesttask.di

import com.sdv.attractgrouptesttask.ui.profile.ProfileListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


    
val viewModelModule = module {
        viewModel{ProfileListViewModel(get())}
}
