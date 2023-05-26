package com.orra.pet.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T, LD : LiveData<T>> Fragment.observeLD(liveData: LD, crossinline block: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { block(it) })
}

inline fun <reified T, LD : LiveData<T>> AppCompatActivity.observeLD(liveData: LD, crossinline block: (T) -> Unit) {
    liveData.observe(this, Observer { block(it) })
}
