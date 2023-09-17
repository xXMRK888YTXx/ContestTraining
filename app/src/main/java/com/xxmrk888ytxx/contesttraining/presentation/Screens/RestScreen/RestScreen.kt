package com.xxmrk888ytxx.contesttraining.presentation.Screens.RestScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RestScreen(
    restViewModel: RestViewModel = hiltViewModel(),
) {
    val rates by restViewModel.rates.collectAsState(initial = emptyList())

    val lastRest by restViewModel.lastRate.collectAsState(initial = null)

    val isError by restViewModel.isError.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(isError) {
            Text(text = "An error has occurred")

            TextButton(onClick = { restViewModel.requestNewData() }) {
                Text(text = "Retry")
            }
        } else {
            Text(text = "Refinince rate is ${lastRest?.value ?: "No data"} on ${lastRest?.date ?: "No data"})")
        }

        Text(text = "History of requests")

        LazyColumn(

        ) {
            items(rates) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Date:${it.date}")
                        Text(text = "Rate:${it.value}")
                    }
                }
            }
        }
    }
}