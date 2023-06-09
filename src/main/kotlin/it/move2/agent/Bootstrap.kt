package it.move2.agent

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.rabbitmq.client.ConnectionFactory
import it.move2.agent.adapters.justjoinit.JustJoinIT
import it.move2.agent.adapters.rabbitmq.RabbitMQ
import it.move2.agent.application.Publisher
import it.move2.agent.application.Updater
import it.move2.agent.application.Scheduler
import it.move2.agent.configuration.APIConfiguration
import it.move2.agent.configuration.RabbitMQConfiguration
import it.move2.agent.configuration.SchedulerConfiguration
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit.MILLISECONDS

object Bootstrap {

    private val httpClient = OkHttpClient.Builder().callTimeout(5000, MILLISECONDS).build()
    private val mapper = ObjectMapper().registerKotlinModule()
    private val justJoinItConfiguration = APIConfiguration("https://justjoin.it/api/offers")

    private val justJoinIt = JustJoinIT(httpClient, mapper, justJoinItConfiguration)

    private val jobBoards = listOf(justJoinIt)

    private val rabbitMQConfiguration = RabbitMQConfiguration(
        System.getenv("USERNAME"),
        System.getenv("PASSWORD"),
        System.getenv("HOST"),
        System.getenv("VIRTUALHOST"),
        System.getenv("PORT").toInt(),
        System.getenv("QUEUE")
    )

    private val connectionFactory = ConnectionFactory().apply {
        username = rabbitMQConfiguration.username
        password = rabbitMQConfiguration.password
        host = rabbitMQConfiguration.host
        virtualHost = rabbitMQConfiguration.virtualHost
        port = rabbitMQConfiguration.port
    }

    private val channel = connectionFactory.newConnection().createChannel()

    private val messageProducer = RabbitMQ(channel, rabbitMQConfiguration)

    private val publisher = Publisher(messageProducer)

    private val handler = Updater(jobBoards, publisher)
    private val schedulerConfiguration = SchedulerConfiguration(60000)

    fun scheduler() = Scheduler(handler, schedulerConfiguration)
}