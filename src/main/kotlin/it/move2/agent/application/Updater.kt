package it.move2.agent.application

import it.move2.agent.ports.JobBoard
import it.move2.job.JobOffer

class Updater(
    private val jobBoards: List<JobBoard>, private val publisher: Publisher
) {
    //    fun execute() = jobBoards.map { it.getJobs() }.flatten().let { publisher.publish(it) }
    fun execute() {
        val jobList = mutableListOf<JobOffer>()
        for (jobBoard in jobBoards) {
            val jobs = jobBoard.getJobs()
            for (job in jobs) {
                jobList.add(job)
            }
        }
        publisher.publish(jobList)
    }
}