package it.move2.agent.adapters.rabbitmq

import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import it.move2.agent.ports.Queue

data class RabbitMQConfiguration(
    val username: String,
    val password: String,
    val host: String,
    val virtualHost: String,
    val port: Int,
    val queue: String
)

class RabbitMQFactory {
    companion object {
        private fun connectionFactory(configuration: RabbitMQConfiguration) = ConnectionFactory().apply {
            username = configuration.username
            password = configuration.password
            host = configuration.host
            virtualHost = configuration.virtualHost
            port = configuration.port
        }

        private fun channel(rabbitMQConfiguration: RabbitMQConfiguration) =
            connectionFactory(rabbitMQConfiguration).newConnection().createChannel()

        fun queue(rabbitMQConfiguration: RabbitMQConfiguration) =
            RabbitMQ(channel(rabbitMQConfiguration), rabbitMQConfiguration.queue)
    }
}

class RabbitMQ(
    private val channel: Channel, private val queue: String
) : Queue {

    override fun pub(json: String) = channel.basicPublish("", queue, null, json.toByteArray())
}