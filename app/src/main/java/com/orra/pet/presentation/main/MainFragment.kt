package com.orra.pet.presentation.main

import android.os.Bundle
import android.view.View
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
import com.orra.pet.core_presentation.base.BaseFragment
import com.orra.pet.core_presentation.utils.fragmentViewModel
import com.orra.pet.core_ui.button.SmallButton
import com.orra.pet.core_ui.navbar.NavBar
import com.orra.pet.core_ui.theme.AppTheme
import com.orra.pet.core_ui.theme.ThemeState
import com.orra.pet.core_ui.theme.ThemeType
import com.orra.pet.presentation.addFragment
import com.orra.pet.presentation.components.base_funs.BaseFunsFragment
import com.orra.pet.presentation.components.buttons.BaseButtonsFragment
import com.orra.pet.presentation.components.photo_picker.PhotoPickerFragment
import com.orra.pet.presentation.components.request.RequestFragment
import com.orra.pet.presentation.components.texts.BaseTextsFragment

class MainFragment : BaseFragment() {

    private val viewModel by fragmentViewModel { MainViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBase(viewModel)
    }

    @Composable
    override fun FragmentContent() {
        Column(
            modifier = Modifier
                .background(AppTheme.colors.background.primary)
        ) {
            NavBar(
                title = "This is MainFragment",
                subtitle = "This is MainFragment",
                onLeftIconClicked = { onBack() }
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                ThemeSwitcher(
                    onSwitchClicked = { isLight ->
                        ThemeState.themeType = if (isLight) ThemeType.LIGHT else ThemeType.DARK
                    }
                )
                SmallButton(
                    text = "Show BaseFundsFragment",
                    onClick = { addFragment(BaseFunsFragment()) }
                )
                SmallButton(
                    text = "Show BaseButtonsFragment",
                    onClick = { addFragment(BaseButtonsFragment()) }
                )
                SmallButton(
                    text = "Show PhotoPickerFragment",
                    onClick = { addFragment(PhotoPickerFragment()) }
                )
                SmallButton(
                    text = "Show BaseTextsFragment",
                    onClick = { addFragment(BaseTextsFragment()) }
                )

                SmallButton(
                    text = "Show RequestFragment",
                    onClick = { addFragment(RequestFragment()) }
                )
            }
        }
    }

    @Composable
    private fun ThemeSwitcher(onSwitchClicked: (Boolean) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SmallButton(
                text = "Set LightTheme",
                onClick = { onSwitchClicked.invoke(true) }
            )
            SmallButton(
                text = "Set DarkTheme",
                onClick = { onSwitchClicked.invoke(false) }
            )
        }
    }
}