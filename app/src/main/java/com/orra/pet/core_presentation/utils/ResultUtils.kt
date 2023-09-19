package com.orra.pet.core_presentation.utils


inline fun <T> asResult(block: () -> T) = runCatching(block)
