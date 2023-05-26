package com.orra.pet.utils


inline fun <T> asResult(block: () -> T) = runCatching(block)
