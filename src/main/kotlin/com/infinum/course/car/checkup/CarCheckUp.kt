package com.infinum.course.car.checkup

import java.time.LocalDateTime

data class CarCheckUp(
    val performedAt: LocalDateTime,
    val car: Car,
    val workerName: String,
    val price: Int,
    val carID: Long
)