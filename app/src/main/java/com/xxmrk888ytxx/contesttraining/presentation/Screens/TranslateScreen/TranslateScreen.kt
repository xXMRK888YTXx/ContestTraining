package com.xxmrk888ytxx.contesttraining.presentation.Screens.TranslateScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TranslateScreen(
    translateViewModel: TranslateViewModel = hiltViewModel()
) {


    val text = remember {
        mutableStateOf("")
    }

    val result = translateViewModel.result.collectAsState()

    Column {
        OutlinedTextField(value = text.value,
            onValueChange = {
                text.value = it
            })

        Text(text = "Result: ${result.value}")

        Button(onClick = { translateViewModel.updateTranslateResult(text.value) }) {
            Text("Translate")
        }
    }


}