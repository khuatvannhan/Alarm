package com.nhankv.util

import android.databinding.*


fun ObservableBoolean.setBoolean(isShow: Boolean) {
    set(isShow)
}

fun ObservableInt.setInt(value: Int) {
    set(value)
}

fun ObservableLong.setLong(value: Long) {
    set(value)
}

fun ObservableField.setString(value: String) {
    set
}