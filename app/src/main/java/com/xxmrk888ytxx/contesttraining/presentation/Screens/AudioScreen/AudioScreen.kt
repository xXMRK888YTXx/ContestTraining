package com.xxmrk888ytxx.contesttraining.presentation.Screens.AudioScreen

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


private class SpeechToTextContract : ActivityResultContract<String,String?>() {

    override fun createIntent(context: Context, input: String): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {

            this.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            // Adding an extra language, you can use any language from the Locale class.
            this.putExtra(RecognizerIntent.EXTRA_LANGUAGE, input)
            // Text that shows up on the Speech input prompt.

            putExtra(RecognizerIntent.EXTRA_PROMPT,"Speck now")
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val result = intent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

        return try {
            result?.get(0) ?: return null
        }catch (e:IndexOutOfBoundsException) { null }
    }

}


@Composable
fun AudioScreen() {

    var lastText by remember {
        mutableStateOf("")
    }

    val contract = rememberLauncherForActivityResult(
        contract = SpeechToTextContract(),
        onResult = {
            lastText = it ?: ""
        }
    )



    Column {
        Text(text = "Result:$lastText")

        Button(onClick = { contract.launch("en") }) {

            Text(text = "English")
        }

        Button(onClick = { contract.launch("ru") }) {

            Text(text = "Russia")
        }
    }
}