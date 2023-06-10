package it.move2.agent.application

import it.move2.job.JobOffer
import it.move2.agent.ports.Queue

class Publisher(private val queue: Queue) {
    fun publish(jobOffer: JobOffer) = queue.push(jobOffer)
}