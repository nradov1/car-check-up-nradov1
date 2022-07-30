package com.infinum.course.car.checkup.carmodel.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name="car_models")
data class CarModels(
    @Id
    val id: UUID = UUID.randomUUID(),
    val manufacturer:String,
    val model:String,

    )