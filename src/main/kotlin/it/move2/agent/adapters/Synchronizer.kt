package it.move2.agent.adapters

import it.move2.agent.ports.Handler
import it.move2.agent.ports.JobOffers

class Synchronizer(private val jobOffers: JobOffers) : Handler {
    override fun handle() {
        jobOffers.getJobs()
    }
}