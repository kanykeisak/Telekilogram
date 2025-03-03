package com.example.telekilogram.di

import com.example.telekilogram.data.core.RetrofitClient
import com.example.telekilogram.data.service.ChatServiceImpl
import com.example.telekilogram.ui.fragments.chat.ChatViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { RetrofitClient(androidContext()) }
    single { get<RetrofitClient>().retrofitService }
    single { ChatServiceImpl(get()) }
}

val viewModelModule = module() {
    viewModel { ChatViewModel() }
}