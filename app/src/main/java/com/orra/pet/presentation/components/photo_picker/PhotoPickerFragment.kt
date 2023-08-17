package com.orra.pet.presentation.components.photo_picker

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.orra.core_ui.button.SmallButton
import com.orra.core_ui.navbar.NavBar
import com.orra.core_ui.theme.AppTheme
import com.orra.core_presentation.base.BaseFragment
import com.orra.pet.picker.PickSinglePhotoContract

class PhotoPickerFragment : BaseFragment() {

    private val singlePhotoPickerLauncher =
        registerForActivityResult(PickSinglePhotoContract()) { imageUri: Uri? ->
            uri.value = imageUri
        }

    private var uri = MutableLiveData<Uri>(null)
    private fun pickPhoto() = singlePhotoPickerLauncher.launch(null)

    @Composable
    override fun FragmentContent() {
        Column(modifier = Modifier.background(AppTheme.colors.background.primary)) {

            NavBar(
                title = "This is PhotoPickerFragment",
                subtitle = "This is PhotoPickerFragment",
                onLeftIconClicked = { onBack() }
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                SmallButton(
                    text = "Pick photo",
                    onClick = ::pickPhoto
                )

                uri.observeAsState().value?.let {
                    Image(it, 200)
                    Image(it, 100)
                    Image(it, 40)
                }
            }
        }
    }

    @Composable
    private fun Image(uri: Uri, size: Int) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = uri)
                    .build()
            ),
            contentDescription = null,
            modifier = Modifier
                .size(size.dp)
                .border(2.dp, Color.Gray),
            contentScale = ContentScale.Crop
        )
    }
}