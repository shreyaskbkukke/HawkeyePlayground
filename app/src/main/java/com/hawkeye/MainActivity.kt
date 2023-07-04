package com.hawkeye

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import com.hawkeye.ui.theme.HawkeyePlaygroundTheme
import com.hawkeye.ui.theme.gray
import com.hawkeye.authModule.util.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = gray.toArgb()
        window.navigationBarColor = gray.toArgb()
        setContent {
            HawkeyePlaygroundTheme {
                Navigation()
            }
        }
    }
}