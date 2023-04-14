package it.move2.agent.adapters.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import it.move2.agent.configuration.JobsConfiguration
import it.move2.agent.model.JobOffer
import it.move2.agent.ports.JobOffers
import okhttp3.OkHttpClient
import okhttp3.Request

class JustJoinIT(
    private val client: OkHttpClient, private val mapper: ObjectMapper, private val configuration: JobsConfiguration
) : JobOffers {

    override fun getJobs(): List<JobOffer> {
        val request = Request.Builder().url(configuration.path).build()
        val response = client.newCall(request).execute()
        val data: List<JustJoinITJobOffer> = mapper.readValue(response.body?.string()!!)
        return data.map { JobOffer(it.id, configuration.path) }
    }
}