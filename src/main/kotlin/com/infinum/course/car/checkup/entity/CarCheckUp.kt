package com.infinum.course.car.checkup.entity

import java.time.LocalDateTime

data class CarCheckUp(
    val performedAt: LocalDateTime,
    val workerName: String,
    val price: Int,
    val carID: Long
)