package it.move2.agent

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import it.move2.agent.adapters.Synchronizer
import it.move2.agent.adapters.http.JustJoinIT
import it.move2.agent.application.Scheduler
import it.move2.agent.configuration.JobsConfiguration
import it.move2.agent.configuration.SchedulerConfiguration
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit.MILLISECONDS

object Bootstrap {

    private val httpClient = OkHttpClient.Builder().callTimeout(5000, MILLISECONDS).build()
    private val mapper = ObjectMapper().registerKotlinModule()
    private val jobOffersConfiguration = JobsConfiguration("https://justjoin.it/api/offers")

    private val jobOffers = JustJoinIT(httpClient, mapper, jobOffersConfiguration)

    private val handler = Synchronizer(jobOffers)
    private val schedulerConfiguration = SchedulerConfiguration(5000)
    fun scheduler() = Scheduler(handler, schedulerConfiguration)
}