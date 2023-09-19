package com.orra.pet.presentation.components.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orra.pet.core_ui.navbar.NavBar
import com.orra.pet.core_ui.text.BodyText
import com.orra.pet.core_ui.text.Title
import com.orra.pet.core_ui.theme.AppTheme
import com.orra.pet.core_presentation.base.BaseFragment

class BaseTextsFragment : BaseFragment() {

    @Composable
    override fun FragmentContent() {
        Column(modifier = Modifier.background(AppTheme.colors.background.primary)) {
            NavBar(
                title = "This is BaseTextsFragment",
                subtitle = "This is BaseTextsFragment",
                onLeftIconClicked = { onBack() }
            )
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Title(title = "this is ScreenTitle example")
                Title(title = "this is ScreenTitle example", subtitle = "with subtitle")
                BodyText(text = "this is Paragraph example")
            }
        }
    }
}