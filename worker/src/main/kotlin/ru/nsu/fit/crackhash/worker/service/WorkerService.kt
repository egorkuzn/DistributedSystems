package ru.nsu.fit.crackhash.worker.service

import ru.nsu.fit.crackhash.worker.model.dto.WorkerTaskDto

interface WorkerService {
    fun takeTask(crackRequest: WorkerTaskDto)
}