package it.move2.agent.ports

import it.move2.agent.domain.JobOffer

interface JobBoards {
    fun getJobs(): List<JobOffer>
}