package com.nhankv.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.EditText

class EditTextCustom: EditText {
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    fun init(context: Context) {
        val face = Typeface.createFromAsset(context.assets, "fonts/agency.ttf")
        typeface = face
    }
}
