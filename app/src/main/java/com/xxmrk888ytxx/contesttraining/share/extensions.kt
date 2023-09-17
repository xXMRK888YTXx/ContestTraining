package com.xxmrk888ytxx.contesttraining.share

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.CompositionLocalProvider


fun ComponentActivity.setContentWithNavigator(
    navigator: Navigator,
    parent: CompositionContext? = null,
    content: @Composable () -> Unit
) {
    setContent(parent) {

        CompositionLocalProvider(
            LocalNavigator provides navigator
        ) {
            content()
        }
    }
}