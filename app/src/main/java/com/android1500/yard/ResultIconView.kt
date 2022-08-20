package com.android1500.yard

import android.content.Context
import android.util.AttributeSet

class ResultIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private fun showPass() = setImageResource(R.drawable.ic_baseline_check_circle_outline_24)

    private fun showFail() = setImageResource(R.drawable.ic_outline_cancel_24)

    fun update(isRooted: Boolean) {
        if (isRooted) {
            showFail()
        } else {
            showPass()
        }
    }
}