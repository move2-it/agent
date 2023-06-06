package it.move2.agent.application

import it.move2.agent.configuration.SchedulerConfiguration
import kotlin.concurrent.fixedRateTimer

class Scheduler(
    private val jobsUpdater: JobsUpdater, private val configuration: SchedulerConfiguration
) {
    fun start() {
        fixedRateTimer(period = configuration.interval) {
            jobsUpdater.execute()
        }
    }
}

