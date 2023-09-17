package com.xxmrk888ytxx.contesttraining.presentation.Screens.LanguageScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.xxmrk888ytxx.contesttraining.R

@Composable
fun LanguageScreen(
    languageViewModel: LanguageViewModel = hiltViewModel(),
) {

    val language = languageViewModel.currentLANGUAGE.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.i_am_not_russian))

        Text(text = language.value.name)

        Button(onClick = { languageViewModel.setRussian() }) {
            Text(text = "Стать русский")
        }

        Button(onClick = { languageViewModel.setEnglish() }) {
            Text(text = "Become not russian")
        }

        Button(onClick = { languageViewModel.setSystem() }) {
            Text(text = "Become a system")
        }
    }
}