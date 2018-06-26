package com.nhankv.customview.dialog

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.nhankv.alarm.databinding.DialogLableBinding
import android.view.WindowManager
import android.view.View.OnFocusChangeListener



class CustomDialog(context: Context, val listener: DialogListener) : Dialog(context) {
    private val TAG = javaClass.name
    private lateinit var viewDialogBinding: DialogLableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        initDatabinding()
        window.setLayout(900, ActionBar.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
    }

    private fun initDatabinding() {
        if (!::viewDialogBinding.isInitialized) {
            viewDialogBinding = DialogLableBinding.inflate(LayoutInflater.from(context))
        }
        setContentView(viewDialogBinding.root)
        viewDialogBinding.listener = listener
    }

    private fun initView() {
        viewDialogBinding.edLabel.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }
    }
}