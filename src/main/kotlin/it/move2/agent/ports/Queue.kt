package it.move2.agent.ports

interface Queue {
    fun pub(json: String)
}