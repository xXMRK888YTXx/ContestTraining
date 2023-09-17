package com.xxmrk888ytxx.contesttraining.presentation.Screens.LanguageScreen

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.LanguageManager.LanguageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val languageManager: LanguageManager
) : ViewModel() {

    val currentLANGUAGE = languageManager.language

    fun setRussian() {
        languageManager.setLanguage(LanguageManager.LANGUAGE.RU)
    }

    fun setEnglish() {
        languageManager.setLanguage(LanguageManager.LANGUAGE.EN)
    }

    fun setSystem() {
        languageManager.setLanguage(LanguageManager.LANGUAGE.SYSTEM)
    }
}