package com.orra.pet.utils

import android.os.Build
import com.orra.pet.BuildConfig

/**
 * Более удобная форма для констант.
 * Возможно пригодится в будущем
 */
object BuildInfo {
    val isDebug = BuildConfig.DEBUG
    val isRelease = !isDebug
    val appVersion = BuildConfig.VERSION_NAME
    val sdkVersion: Int = Build.VERSION.SDK_INT

}