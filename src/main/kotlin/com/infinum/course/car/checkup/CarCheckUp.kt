package com.infinum.course.car.checkup

import java.time.LocalDateTime

data class CarCheckUp constructor(val performedAt: LocalDateTime, val car: Car)