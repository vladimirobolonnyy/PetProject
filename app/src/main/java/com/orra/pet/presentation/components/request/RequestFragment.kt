package com.orra.pet.presentation.components.request

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.orra.core_ui.button.SmallButton
import com.orra.core_ui.formatter.AppFormatter
import com.orra.core_ui.navbar.NavBar
import com.orra.core_ui.text.BodyText
import com.orra.core_ui.theme.AppTheme
import com.orra.core_ui.utils.roundCorners
import com.orra.pet.base.BaseFragment
import com.orra.pet.data.dto.AmericaHoliday
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
            Box(modifier = Modifier.padding(16.dp)) {
                SmallButton(
                    text = "reload",
                    onClick = viewModel::reload
                )
            }
            val state = viewModel.viewState.observeAsState().value
            when {
                state?.isNotEmpty() == true -> Holidays(state)
                state?.isEmpty() == true -> BodyText(text = "No holidays =(")
            }
        }
    }


    @Composable
    private fun Holidays(holidays: List<AmericaHoliday>) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            holidays.forEach { HolidayItem(it) }
        }
    }

    @Composable
    private fun HolidayItem(holiday: AmericaHoliday) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(AppTheme.colors.background.secondary)
                .padding(8.dp)
        ) {
            Text(
                text = holiday.name,
                style = AppTheme.styles.LabelPrimary,
                color = AppTheme.colors.text.primary
            )
            Text(
                text = AppFormatter.formatDate(holiday.date),
                style = AppTheme.styles.LabelSecondary,
                color = AppTheme.colors.text.secondary
            )
        }
    }
}