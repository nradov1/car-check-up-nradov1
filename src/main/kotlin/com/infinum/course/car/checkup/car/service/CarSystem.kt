package com.infinum.course.car.checkup.car.service

import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.helpers.CarDetails
import com.infinum.course.car.checkup.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.repository.CarRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CarSystem(
                   private val carRepository: CarRepository,
                   private val checkUpRepository: CheckUpRepository
) {
    fun addCar(car: CarDetails): Car {
       return carRepository.save(Car(0, LocalDate.now().toString(),car.manufacturer,car.model,car.productionYear,car.vin,))
    }
    fun findCar(id:Long) = carRepository.findCarById(id)

    fun getCheckups(car:Car)=checkUpRepository.findByCar(car)

    fun findAllCars(pageable: Pageable) = carRepository.findAll(pageable)
}