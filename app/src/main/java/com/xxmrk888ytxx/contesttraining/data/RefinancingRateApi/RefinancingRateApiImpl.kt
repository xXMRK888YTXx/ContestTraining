package com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi

import android.util.Log
import com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.models.RefinancingRateRemoteModel
import com.xxmrk888ytxx.contesttraining.data.RefinancingRateApi.models.RefinancingRateResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RefinancingRateApiImpl @Inject constructor(
    private val httpClient: HttpClient
) : RefinancingRateApi {


    private val jsonParser by lazy {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }

    override suspend fun getRefinancingRate(
        year: Int,
        month: Int,
        day: Int,
    ): RefinancingRateRemoteModel = withContext(Dispatchers.IO) {


        val url = "https://api.nbrb.by/refinancingrate?ondate=$year-0$month-$day"

        val request = httpClient.get(url).bodyAsText().drop(1).dropLast(1)
        return@withContext try {
            val responseModel = jsonParser.decodeFromString<RefinancingRateResponse>(
                request
            )
            return@withContext RefinancingRateRemoteModel(
                data = responseModel.data.cutData(),
                refinancingRate = responseModel.value
            )

        }catch (e:Exception) {
            Log.d("def",e.stackTraceToString())

            throw e
        }



    }

    private fun String.cutData() : String {
        var finalString = ""

        forEach {
            if(it == 'T') return finalString
            finalString += it
        }

        return finalString
    }


}