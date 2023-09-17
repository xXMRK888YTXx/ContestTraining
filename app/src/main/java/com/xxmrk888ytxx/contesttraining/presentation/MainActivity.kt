package com.xxmrk888ytxx.contesttraining.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xxmrk888ytxx.contesttraining.presentation.Screens.AudioScreen.AudioScreen
import com.xxmrk888ytxx.contesttraining.presentation.Screens.CameraScreen.CameraScreen
import com.xxmrk888ytxx.contesttraining.presentation.Screens.LanguageScreen.LanguageScreen
import com.xxmrk888ytxx.contesttraining.presentation.Screens.MainScreen.MainScreen
import com.xxmrk888ytxx.contesttraining.presentation.Screens.RestScreen.RestScreen
import com.xxmrk888ytxx.contesttraining.presentation.Screens.TranslateScreen.TranslateScreen
import com.xxmrk888ytxx.contesttraining.share.setContentWithNavigator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var activityViewModelFactory: ActivityViewModel.Factory

    private val activityViewModel by viewModels<ActivityViewModel> { activityViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithNavigator(
            navigator = activityViewModel
        ) {
            val navController = rememberNavController()

            LaunchedEffect(key1 = navController, block = {
                activityViewModel.navController = navController
            })

            NavHost(
                navController = navController,
                startDestination = "main",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                composable("main") {
                    MainScreen()
                }

                composable("rest") {
                    RestScreen()
                }


                composable("lan") {
                    LanguageScreen()
                }

                composable("camera") {
                    CameraScreen()
                }

                composable("audio") {
                    AudioScreen()
                }

                composable("tran") {
                    TranslateScreen()
                }
            }
        }
    }
}
