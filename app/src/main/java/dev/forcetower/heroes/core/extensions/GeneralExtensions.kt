package dev.forcetower.heroes.core.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}