package com.xxmrk888ytxx.contesttraining.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.xxmrk888ytxx.contesttraining.share.Navigator
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ActivityViewModel @Inject constructor(

) : ViewModel(),Navigator {

    var navController:NavController? = null




    class Factory @Inject constructor(
        private val activityViewModel: Provider<ActivityViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return activityViewModel.get() as T
        }
    }

    override fun toRestTrainingScreen() {
        navController?.navigate("rest") { launchSingleTop = true }
    }

    override fun toLanguageScreen() {
        navController?.navigate("lan")
    }

    override fun toCameraScreen() {
        navController?.navigate("camera")
    }

    override fun toAudioScreen() {
        navController?.navigate("audio")
    }

    override fun toTranslationScreen() {
        navController?.navigate("tran")
    }
}