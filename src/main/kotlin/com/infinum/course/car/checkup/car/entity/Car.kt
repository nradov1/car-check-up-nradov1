package com.infinum.course.car.checkup.car.entity

import java.time.LocalDateTime
import javax.persistence.*

//sve ok
@Entity
@Table(name = "car")
data class Car constructor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val createdOn: LocalDateTime,
    val manufacturer: String,
    val model:String,
    val productionYear: Int,
    val vin:String
    )