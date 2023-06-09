package it.move2.agent.configuration

data class RabbitMQConfiguration(
    val username: String,
    val password: String,
    val host: String,
    val virtualHost: String,
    val port: Int,
    val queue: String
)