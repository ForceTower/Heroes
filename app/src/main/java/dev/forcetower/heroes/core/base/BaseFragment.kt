package dev.forcetower.heroes.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.core.view.updatePadding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import dev.forcetower.heroes.core.extensions.doOnApplyWindowInsets
import timber.log.Timber

abstract class BaseFragment : DaggerFragment() {
    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (shouldApplyInsets()) {
            view.doOnApplyWindowInsets { v, insets, padding ->
                Timber.d("Base insets bottom ${insets.systemWindowInsetBottom} ${javaClass.simpleName}")
                v.updatePadding(
                    bottom = padding.bottom + insets.systemWindowInsetBottom,
                    top = padding.top + insets.systemWindowInsetTop,
                    left = padding.left + insets.systemWindowInsetLeft,
                    right = padding.right + insets.systemWindowInsetRight
                )
            }
        }
    }

    fun showSnack(string: String, duration: Int = Snackbar.LENGTH_SHORT) {
        val activity = activity
        if (activity is BaseActivity) {
            activity.showSnack(string, duration)
        } else {
            Timber.i("Not part of BaseActivity")
        }
    }

    fun getSnack(string: String, duration: Int = Snackbar.LENGTH_SHORT): Snackbar? {
        val activity = activity
        return if (activity is BaseActivity) {
            activity.getSnackInstance(string, duration)
        } else {
            Timber.i("Not part of BaseActivity")
            null
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
    }

    open fun shouldApplyInsets() = false
}