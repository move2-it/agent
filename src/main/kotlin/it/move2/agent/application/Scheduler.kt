package it.move2.agent.application

import it.move2.agent.configuration.SchedulerConfiguration
import kotlin.concurrent.fixedRateTimer

class Scheduler(
    private val configuration: SchedulerConfiguration, private val updater: Updater
) {
    fun start() {
        fixedRateTimer(period = configuration.interval) {
            updater.execute()
        }
    }
}

