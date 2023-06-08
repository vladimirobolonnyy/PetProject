package com.orra.pet.picker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

private const val MIME_TYPE_IMAGE = "image/*"


/**
 * Usage in fragment:
 *
 * private var uri = MutableLiveData<Uri>(null)
 *
 * private val singlePhotoPickerLauncher =
 * registerForActivityResult(PickSinglePhotoContract()) { imageUri: Uri? ->
 * uri.value = imageUri
 * }
 *
 * private fun pickPhoto() = singlePhotoPickerLauncher.launch(null)
 * */
class PickSinglePhotoContract : ActivityResultContract<Void?, Uri?>() {

    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(
            if (PhotoPickerAvailabilityChecker.isPhotoPickerAvailable()) {
                Intent(MediaStore.ACTION_PICK_IMAGES)
            } else {
                Intent(Intent.ACTION_OPEN_DOCUMENT)
            }
        ).apply { type = MIME_TYPE_IMAGE }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.data
    }
}