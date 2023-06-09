package it.move2.agent.ports

import it.move2.agent.domain.JobOffer

interface Queue {
    fun push(jobOffer: JobOffer)
}