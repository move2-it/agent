package it.move2.agent.ports

import it.move2.agent.model.JobOffer

interface JobOffers {
    fun getJobs(): List<JobOffer>
}