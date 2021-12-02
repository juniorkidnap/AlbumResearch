package com.whities.albumresearch.framework.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.whities.albumresearch.R
import com.whities.albumresearch.business.navigation.AppNavigation
import com.whities.albumresearch.business.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigation: AppNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigation.navigateTo(Screens.SearchScreen)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
