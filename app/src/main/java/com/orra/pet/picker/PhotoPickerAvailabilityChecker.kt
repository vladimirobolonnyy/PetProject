package com.orra.pet.picker

import android.os.Build
import android.os.ext.SdkExtensions.getExtensionVersion

private const val ANDROID_R_REQUIRED_EXTENSION_VERSION = 2

object PhotoPickerAvailabilityChecker {

    fun isPhotoPickerAvailable(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= 33 -> true
            Build.VERSION.SDK_INT >= 30 -> {
                getExtensionVersion(Build.VERSION_CODES.R) >= ANDROID_R_REQUIRED_EXTENSION_VERSION
            }

            else -> false
        }
    }
}