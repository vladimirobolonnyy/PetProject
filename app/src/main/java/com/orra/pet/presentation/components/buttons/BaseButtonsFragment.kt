package com.orra.pet.presentation.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orra.core_ui.button.SmallButton
import com.orra.core_ui.navbar.NavBar
import com.orra.core_ui.theme.AppTheme
import com.orra.core_ui.theme.ThemeDark
import com.orra.pet.base.BaseFragment

class BaseButtonsFragment : BaseFragment() {

    @Composable
    override fun FragmentContent() {
        Column(modifier = Modifier.background(AppTheme.colors.background.primary)) {

            NavBar(
                title = "This is BaseButtonsFragment",
                subtitle = "This is BaseButtonsFragment",
                onLeftIconClicked = { onBack() }
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SmallButton(
                        text = "Light SmallButton",
                        onClick = {}
                    )
                    SmallButton(
                        enabled = false,
                        text = "Light disabled",
                        onClick = {}
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ThemeDark {
                        SmallButton(
                            text = "Dark SmallButton",
                            onClick = {}
                        )
                        SmallButton(
                            enabled = false,
                            text = "Dark disabled",
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}