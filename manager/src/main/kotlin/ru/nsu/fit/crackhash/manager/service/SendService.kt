package ru.nsu.fit.crackhash.manager.service

interface SendService {
    fun execute(id: String)
    fun sendAfterRabbitReconnect()
}