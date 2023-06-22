package it.move2.agent

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import it.move2.agent.adapters.justjoinit.JustJoinIT
import it.move2.agent.adapters.justjoinit.JustJointITConfiguration
import it.move2.agent.adapters.rabbitmq.RabbitMQConfiguration
import it.move2.agent.adapters.rabbitmq.RabbitMQFactory.Companion.queue
import it.move2.agent.application.Publisher
import it.move2.agent.application.Scheduler
import it.move2.agent.application.Updater
import it.move2.agent.configuration.PublisherConfiguration
import it.move2.agent.configuration.SchedulerConfiguration
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit.MILLISECONDS

object Bootstrap {
    // JustJoinIT
    private val httpClient = OkHttpClient.Builder().callTimeout(5000, MILLISECONDS).build()
    private val mapper = ObjectMapper().registerKotlinModule()
    private val justJoinItConfiguration = JustJointITConfiguration("https://justjoin.it/api/offers")

    private val justJoinIt = JustJoinIT(justJoinItConfiguration, httpClient, mapper)

    // RabbitMQ
    private val rabbitMQConfiguration = RabbitMQConfiguration(
        System.getenv("USERNAME"),
        System.getenv("PASSWORD"),
        System.getenv("HOST"),
        System.getenv("VIRTUALHOST"),
        System.getenv("PORT").toInt(),
        System.getenv("QUEUE")
    )

    private val queue = queue(rabbitMQConfiguration)

    // Application
    private val jobBoards = listOf(justJoinIt)

    private val publisherConfiguration = PublisherConfiguration(5000)

    private val publisher = Publisher(publisherConfiguration, mapper, queue)

    private val handler = Updater(jobBoards, publisher)
    private val schedulerConfiguration = SchedulerConfiguration(60000)

    fun scheduler() = Scheduler(schedulerConfiguration, handler)
}