package com.orra.pet.base.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orra.core_ui.theme.AppTheme
import com.orra.pet.base.Notification
import com.orra.pet.base.getString
import kotlinx.coroutines.delay

@Composable
internal fun InfoView(notification: Notification, delay: Long = 2000) {
    val bgColor = when (notification) {
        is Notification.Error -> AppTheme.colors.static.red
        is Notification.Success -> AppTheme.colors.static.green
        is Notification.Info -> AppTheme.colors.static.blue
    }
    val text = notification.message.getString()
    val isVisible = remember(text) { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible.value,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(bgColor)
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = text,
                style = AppTheme.styles.TitleSecondary,
                color = AppTheme.colors.static.white,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 5,
            )
        }
    }

    LaunchedEffect(key1 = text, block = {
        delay(delay)
        isVisible.value = false
    })
}
