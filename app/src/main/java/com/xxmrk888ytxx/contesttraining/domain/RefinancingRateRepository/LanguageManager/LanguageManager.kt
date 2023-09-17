package com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.LanguageManager

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale
import javax.inject.Inject

class LanguageManager @Inject constructor() {


    fun setLanguage(language: LANGUAGE) {
        AppCompatDelegate
            .setApplicationLocales(LocaleListCompat.create(Locale(language.code)))

        this.language.update { newValue() }
    }

    private fun newValue() = LANGUAGE.entries.firstOrNull { AppCompatDelegate.getApplicationLocales()[0]?.language == it.code } ?: LANGUAGE.SYSTEM

    val language = MutableStateFlow(
        newValue()
    )


    enum class LANGUAGE(val code:String) {
        SYSTEM("xx"),RU("ru"),EN("en")
    }
}