package it.move2.agent.adapters.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import it.move2.agent.configuration.JobsConfiguration
import okhttp3.OkHttpClient
import okhttp3.Request

class JobOffers(
    private val client: OkHttpClient, private val mapper: ObjectMapper, private val configuration: JobsConfiguration
) {

    fun getJobs(): List<JobOffer> {
        val request = Request.Builder().url(configuration.path).build()
        val response = client.newCall(request).execute()
        return mapper.readValue(response.body?.string()!!)
    }
}