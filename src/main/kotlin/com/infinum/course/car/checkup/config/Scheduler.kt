package com.infinum.course.car.checkup.config


import com.infinum.course.car.checkup.carmodel.service.CarModelService
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar

@Configuration
@EnableScheduling
class SchedulerConfiguration(val carModelService: CarModelService) : SchedulingConfigurer {

    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        taskRegistrar.addFixedDelayTask({carModelService.update()},86_400_000)
    }
}