package it.move2.agent.application

import it.move2.agent.configuration.SchedulerConfiguration
import it.move2.agent.ports.Handler
import kotlin.concurrent.fixedRateTimer

class Scheduler(
    private val handler: Handler, private val configuration: SchedulerConfiguration
) {
    fun start() {
        fixedRateTimer(period = configuration.interval) {
            handler.handle()
        }
    }
}

