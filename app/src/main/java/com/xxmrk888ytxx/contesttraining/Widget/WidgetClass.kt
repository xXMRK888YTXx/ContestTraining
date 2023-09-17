package com.xxmrk888ytxx.contesttraining.Widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.RefinancingRateModel
import com.xxmrk888ytxx.contesttraining.domain.RefinancingRateRepository.RefinancingRateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WidgetClass(
    private val refinancingRateRepository: RefinancingRateRepository
) : GlanceAppWidget() {

    private val scope = CoroutineScope(Dispatchers.Default)

    private var rate:RefinancingRateModel? = null

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        scope.launch {
            refinancingRateRepository.lastRate.collect {
                rate = it

                this@WidgetClass.updateAll(context)
            }
        }

        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize().background(Color.White)
            ) {
                Text(text = "Refinancing rate is ${rate?.value}")

                Button(text = "Update", onClick = {
                    scope.launch {
                        rate = refinancingRateRepository.lastRate.first()

                        this@WidgetClass.updateAll(context)
                    }
                })
            }
        }
    }
}