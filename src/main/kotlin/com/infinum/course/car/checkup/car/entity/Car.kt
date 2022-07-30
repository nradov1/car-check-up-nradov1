package com.infinum.course.car.checkup.car.entity

import com.infinum.course.car.checkup.carmodel.entity.CarModels
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
    val productionYear: Int,
    val vin:String,

    @ManyToOne
    @JoinColumn(name="car_model_id")
    val carModel: CarModels
    )