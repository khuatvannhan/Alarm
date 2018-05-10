package com.nhankv.base

interface BaseView<T> {
    fun setPresenter(presenter: T)
}