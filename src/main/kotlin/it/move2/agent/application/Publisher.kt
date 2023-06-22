package it.move2.agent.application

import com.fasterxml.jackson.databind.ObjectMapper
import it.move2.agent.configuration.PublisherConfiguration
import it.move2.agent.ports.Queue
import it.move2.job.JobOffer

class Publisher(private val configuration: PublisherConfiguration, private val objectMapper: ObjectMapper, private val queue: Queue) {
    fun publish(jobOffers: List<JobOffer>) =
        jobOffers.chunked(configuration.batchSize).forEach { list -> objectMapper.writeValueAsString(list).let { queue.pub(it) } }
}