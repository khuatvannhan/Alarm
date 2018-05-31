package com.nhankv.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity


fun <T: ViewModel> AppCompatActivity(viewModelClass: Class<T>) =
        ViewModelProviders.of(this,  ViewModelFra)