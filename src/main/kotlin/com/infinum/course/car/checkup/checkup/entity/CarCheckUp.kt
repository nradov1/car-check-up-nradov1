package com.infinum.course.car.checkup.checkup.entity


import com.infinum.course.car.checkup.car.entity.Car
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import javax.persistence.*

//sve ok
@Entity
@Table(name="check_ups")
data class CarCheckUp(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val performedAt: LocalDateTime,
    val workerName: String,
    val price: Int,

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="car_id")
    val car: Car

)