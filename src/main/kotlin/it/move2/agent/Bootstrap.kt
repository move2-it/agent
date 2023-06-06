package it.move2.agent

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import it.move2.agent.adapters.justjoinit.JustJoinIT
import it.move2.agent.application.JobsUpdater
import it.move2.agent.application.Scheduler
import it.move2.agent.configuration.AdapterConfiguration
import it.move2.agent.configuration.SchedulerConfiguration
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit.MILLISECONDS

object Bootstrap {

    private val httpClient = OkHttpClient.Builder().callTimeout(5000, MILLISECONDS).build()
    private val mapper = ObjectMapper().registerKotlinModule()
    private val justJoinItConfiguration = AdapterConfiguration("https://justjoin.it/api/offers")

    private val justJoinIt = JustJoinIT(httpClient, mapper, justJoinItConfiguration)

    private val jobBoards = listOf(justJoinIt)

    private val handler = JobsUpdater(jobBoards)
    private val schedulerConfiguration = SchedulerConfiguration(5000)

    fun scheduler() = Scheduler(handler, schedulerConfiguration)
}