package it.move2.agent.adapters.rabbitmq

import com.rabbitmq.client.Channel
import it.move2.agent.configuration.RabbitMQConfiguration
import it.move2.agent.domain.JobOffer
import it.move2.agent.ports.Queue

class RabbitMQ(
    private val channel: Channel,
    private val configuration: RabbitMQConfiguration
): Queue {

    override fun push(jobOffer: JobOffer) =
        channel.basicPublish("", configuration.queue, null, jobOffer.toString().toByteArray())
}