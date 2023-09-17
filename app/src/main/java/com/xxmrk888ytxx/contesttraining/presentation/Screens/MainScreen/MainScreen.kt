package com.xxmrk888ytxx.contesttraining.presentation.Screens.MainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.xxmrk888ytxx.contesttraining.share.LocalNavigator

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
) {

    data class MenuItem(
        val title:String,
        val onAction:() -> Unit
    )

    val navigator = LocalNavigator.current

    val items = remember {
        listOf(
            MenuItem(
                title = "REST trainint(Ставка рефенсирования)",
                onAction = { navigator.toRestTrainingScreen() }
            ),
            MenuItem(
                title = "Language",
                onAction = { navigator.toLanguageScreen() }
            ),
            MenuItem(
                title = "Camera",
                onAction = { navigator.toCameraScreen() }
            ),
            MenuItem(
                title = "Audio",
                onAction = { navigator.toAudioScreen() }
            ),
            MenuItem(
                title = "Translation",
                onAction = { navigator.toTranslationScreen() }
            )
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddings ->
        LazyColumn(
            modifier = Modifier.padding(paddings)
        ) {
            items(items) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp)
                        .padding(16.dp)
                        .clickable { it.onAction() }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}