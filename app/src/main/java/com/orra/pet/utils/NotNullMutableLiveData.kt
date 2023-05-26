package com.orra.pet.utils

import androidx.lifecycle.MutableLiveData

class NotNullMutableLiveData<T : Any>(value: T) : MutableLiveData<T>(value) {

    constructor(initialValue: () -> T): this(initialValue.invoke())

    override fun getValue(): T = super.getValue()!!

}