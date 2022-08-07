package com.atplatform.test.base

import android.os.Bundle
import android.view.*
import android.view.KeyEvent.KEYCODE_BACK
import android.view.Window.FEATURE_NO_TITLE
import androidx.fragment.app.DialogFragment
import com.atplatform.test.R


/**
 * @date  2019/5/31 0031.
 * @author Administrator
 */
abstract class BaseDialog : DialogFragment() {

    open fun initView(){}
    protected open fun isCancelableOutside(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.BaseDialog)
        dialog?.run {
            requestWindowFeature(FEATURE_NO_TITLE)
            setCancelable(isCancelable)
            setCanceledOnTouchOutside(isCancelableOutside())
            setOnKeyListener { _, keyCode, event ->
                keyCode == KEYCODE_BACK &&
                        event.action == KeyEvent.ACTION_DOWN &&
                        !isCancelable
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            window?.setWindowAnimations(getAnimRes())
            window?.attributes = window?.attributes?.apply {
                width = getDialogWidth()
                height = getDialogHeight()
                dimAmount = getDimAmount()
                gravity = getGravity()
            }
        }
    }


    protected open fun getDialogWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    protected open fun getDialogHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    protected open fun getGravity(): Int {
        return Gravity.BOTTOM
    }

    protected open fun getDimAmount(): Float {
        return 0.2f
    }

    protected open fun getAnimRes(): Int {
        return R.style.dialogAnim
    }
}