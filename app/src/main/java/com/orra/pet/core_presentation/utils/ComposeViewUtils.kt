package com.orra.pet.core_presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.orra.pet.core_ui.theme.PetProjectTheme

fun <V> ComposeView.setNonNullContent(value: V?, render: @Composable (V) -> Unit) {
    setThemedContent {
        value?.let { render.invoke(it) }
    }
}

fun ComposeView.setConditionalContent(condition: Boolean, render: @Composable () -> Unit) {
    setThemedContent {
        if (condition) {
            render.invoke()
        }
    }
}

fun ComposeView.setThemedContent(content: @Composable () -> Unit) {
    setContent {
        com.orra.pet.core_ui.theme.PetProjectTheme {
            content.invoke()
        }
    }
}