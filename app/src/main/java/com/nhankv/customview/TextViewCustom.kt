package com.nhankv.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import android.graphics.Typeface


@SuppressLint("AppCompatCustomView")
class TextViewCustom : TextView {
    private var isHoverRegister = false

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    fun setHoverRegister(context: Context, value: Boolean) {
        isHoverRegister = value
        if (isHoverRegister) {
            addEvent(context)
        }
    }

    fun init(context: Context) {
        val face = Typeface.createFromAsset(context.assets, "fonts/agency.ttf")
        typeface = face
    }

    @SuppressLint("ResourceAsColor")
    private fun addEvent(context: Context) {
        setOnClickListener {
            setTextColor(android.R.color.white)
        }
    }
}
