package it.move2.agent.application

import it.move2.agent.configuration.SchedulerConfiguration
import kotlin.concurrent.fixedRateTimer

class Scheduler(
    private val updater: Updater, private val configuration: SchedulerConfiguration
) {
    fun start() {
        fixedRateTimer(period = configuration.interval) {
            updater.execute()
        }
    }
}

