package com.xxmrk888ytxx.contesttraining.share

import androidx.compose.runtime.compositionLocalOf

val LocalNavigator = compositionLocalOf<Navigator> { error("Navigator not provided") }