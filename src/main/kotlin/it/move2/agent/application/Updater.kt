package it.move2.agent.application

import it.move2.agent.ports.JobBoard

class Updater(
    private val jobBoards: List<JobBoard>, private val publisher: Publisher
) {
    fun execute() = jobBoards.map { it.getJobs() }.flatten().let { publisher.publish(it) }
}