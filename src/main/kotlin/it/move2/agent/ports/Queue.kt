package it.move2.agent.ports

import it.move2.job.JobOffer

interface Queue {
    fun push(jobOffer: JobOffer)
}