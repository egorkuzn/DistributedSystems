package ru.nsu.fit.crackhash.worker.config

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.binder.rabbit.config.RabbitConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(RabbitConfiguration::class)
@EnableRabbit
class RabbitConfig(@Value("\${worker.number}") private val workerNumber: String) {
    @Bean("m2w-queue")
    fun queueManagerToWorker() = Queue("manager-to-worker", true)

    @Bean("w2m-queue")
    fun queueWorkerToManager() = Queue("worker-to-manager", true)

    @Bean("m2w-direct")
    fun directExchangeManagerToWorker() = DirectExchange("manager-to-worker")

    @Bean("w2m-direct")
    fun directExchangeWorkerToManager() = DirectExchange("worker-to-manager")

    @Bean
    fun workerBinding(
        @Qualifier("m2w-queue") queue: Queue,
        @Qualifier("m2w-direct") direct: DirectExchange,
    ) = BindingBuilder.bind(queue)
        .to(direct)
        .with(workerNumber)

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()
}