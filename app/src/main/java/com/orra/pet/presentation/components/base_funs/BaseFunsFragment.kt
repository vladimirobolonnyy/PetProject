package com.orra.pet.presentation.components.base_funs

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orra.pet.core_presentation.base.BaseFragment
import com.orra.pet.core_ui.button.SmallButton
import com.orra.pet.core_ui.navbar.NavBar
import com.orra.pet.core_ui.theme.AppTheme
import com.orra.pet.core_presentation.utils.fragmentViewModel

class BaseFunsFragment : BaseFragment() {

    private val viewModel by fragmentViewModel { BaseFunsViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBase(viewModel)
    }

    @Composable
    override fun FragmentContent() {
        Column(modifier = Modifier.background(AppTheme.colors.background.primary)) {
            NavBar(
                title = "This is BaseFunsFragment",
                subtitle = "This is BaseFunsFragment",
                onLeftIconClicked = { onBack() }
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                SmallButton(
                    text = "showError",
                    onClick = viewModel::showError
                )
                SmallButton(
                    text = "showSuccess",
                    onClick = viewModel::showSuccess
                )
                SmallButton(
                    text = "showInfo",
                    onClick = viewModel::showInfo
                )
                SmallButton(
                    text = "showStaticError",
                    onClick = viewModel::showStaticError
                )
                SmallButton(
                    text = "hideStaticError",
                    onClick = viewModel::hideStaticError
                )
                SmallButton(
                    text = "showAndHideLoading",
                    onClick = viewModel::showAndHideLoading
                )
            }
        }
    }
}