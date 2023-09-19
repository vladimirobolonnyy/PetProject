package com.orra.pet.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.orra.pet.R
import com.orra.pet.presentation.main.MainFragment
import com.orra.pet.core_presentation.utils.className

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openMainFragment()
    }

    private fun openMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commitNow()
    }

}

internal fun Fragment.addFragment(fragment: Fragment) {
    val activity = this.activity ?: return
    val tag = fragment.className()
    activity.supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment, tag)
        .addToBackStack(tag)
        .commit()
}