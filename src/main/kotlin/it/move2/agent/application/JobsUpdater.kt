package it.move2.agent.application

import it.move2.agent.ports.JobBoards

class JobsUpdater(
    private val jobBoards: List<JobBoards>
) {
    fun execute() {
        jobBoards.map { it.getJobs() }
    }
}