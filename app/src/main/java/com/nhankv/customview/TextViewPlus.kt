package com.nhankv.customview

import android.content.Context
import android.widget.TextView
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import com.nhankv.alarm.R

class TextViewPlus : TextView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setTypeface(Typeface.createFromAsset(context.resources.assets, "fonts/agency.ttf"))
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setTypeface(Typeface.createFromAsset(context.resources.assets, "fonts/agency.ttf"))
    }

    companion object {
        private val TAG = "TextView"
    }

}