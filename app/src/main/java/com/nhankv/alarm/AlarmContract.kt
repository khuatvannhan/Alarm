package com.nhankv.alarm

import com.nhankv.base.BasePresenter
import com.nhankv.base.BaseView

interface AlarmContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {
    }
}