package dev.forcetower.heroes.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun View.inflater(): LayoutInflater = LayoutInflater.from(context)

inline fun <reified T : ViewDataBinding> ViewGroup.inflate(@LayoutRes res: Int, attachToParent: Boolean = false): T {
    val inflater = inflater()
    return DataBindingUtil.inflate(inflater, res, this, attachToParent)
}

inline fun <reified T : ViewDataBinding> LayoutInflater.inflate(@LayoutRes res: Int): T {
    return DataBindingUtil.inflate(this, res, null, false)
}

fun View.doOnApplyWindowInsets(f: (View, WindowInsets, InitialPadding) -> Unit) {
    val initialPadding = recordInitialPaddingForView(this)
    setOnApplyWindowInsetsListener { v, insets ->
        f(v, insets, initialPadding)
        insets
    }
    requestApplyInsetsWhenAttached()
}

fun View.fadeIn() {
    val fadeInAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
    visibility = View.VISIBLE
    startAnimation(fadeInAnim)
    requestLayout()
}

private fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

data class InitialPadding(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)