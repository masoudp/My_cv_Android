package com.mpakbaz.mycv.features.common

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mpakbaz.mycv.R
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView : LinearLayout {

    private var mErrorListener: ErrorListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        LayoutInflater.from(context).inflate(R.layout.view_error, this)

        button_reload.setOnClickListener {
            mErrorListener?.onReloadData()
        }
    }

    fun setErrorListener(errorListener: ErrorListener) {
        mErrorListener = errorListener
    }

    interface ErrorListener {
        fun onReloadData()
    }
}
