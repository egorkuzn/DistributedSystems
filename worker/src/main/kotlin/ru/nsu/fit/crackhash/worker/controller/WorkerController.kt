package ru.nsu.fit.crackhash.worker.controller

import org.springframework.web.bind.annotation.*
import ru.nsu.fit.crackhash.worker.model.dto.CrackRequestDto
import ru.nsu.fit.crackhash.worker.service.WorkerService

@RestController
@RequestMapping("/api/hash/crack")
class WorkerController(private val workerService: WorkerService) {
    @PatchMapping
    fun takeTask(@RequestBody crackRequest: CrackRequestDto) = workerService.takeTask(crackRequest)

    @GetMapping
    fun getTasksResults() = workerService.getTasksResults()
}