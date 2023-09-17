package com.xxmrk888ytxx.contesttraining.Widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.RefinancingRateRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WidgetHost constructor() : GlanceAppWidgetReceiver() {

    @Inject
    lateinit var refinancingRateRepository: RefinancingRateRepository

    override val glanceAppWidget: GlanceAppWidget by lazy { WidgetClass(refinancingRateRepository) }
}