package it.move2.agent.adapters

import it.move2.agent.adapters.http.JobOffers
import it.move2.agent.ports.Handler

class Synchronizer(private val jobOffers: JobOffers) : Handler {
    override fun handle() {
        jobOffers.getJobs()
    }
}