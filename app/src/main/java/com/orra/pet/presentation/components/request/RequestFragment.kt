package com.orra.pet.presentation.components.request

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orra.core_ui.button.SmallButton
import com.orra.core_ui.navbar.NavBar
import com.orra.core_ui.text.BodyText
import com.orra.core_ui.theme.AppTheme
import com.orra.pet.base.BaseFragment
import com.orra.pet.utils.fragmentViewModel

class RequestFragment : BaseFragment() {

    private val viewModel by fragmentViewModel { RequestViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBase(viewModel)
    }

    @Composable
    override fun FragmentContent() {
        Column(modifier = Modifier.background(AppTheme.colors.background.primary)) {
            NavBar(
                title = "This is RequestFragment",
                subtitle = "This is RequestFragment",
                onLeftIconClicked = { onBack() }
            )
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Box(modifier = Modifier.padding(16.dp)) {
                    SmallButton(
                        text = "reload",
                        onClick = viewModel::reload
                    )
                }
                val state = viewModel.viewState.observeAsState().value
                if (state.isNullOrEmpty().not()) {
                    state?.forEach {
                        BodyText(text = "Hello ${it}!")
                    }
                }
            }
        }
    }
}