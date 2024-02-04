package ru.nsu.fit.crackhash.manager.config.scheduler

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import ru.nsu.fit.crackhash.manager.service.RecieveService
import ru.nsu.fit.crackhash.manager.service.SendService

@EnableAsync
@Configuration
@EnableScheduling
class ManagerScheduler(
    private val sendService: SendService,
    private val recieveService: RecieveService,
) {

    @Async
    @Scheduled(cron = "\${workers.scheduler.interval}")
    fun sendToWorkers() {
        sendService.execute()
    }

    @Async
    @Scheduled(cron = "\${workers.scheduler.interval}")
    fun recieveFromWorkers() {
        recieveService.execute()
    }
}