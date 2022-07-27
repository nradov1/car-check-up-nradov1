package com.infinum.course.car.checkup.car.service

import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.car.controller.CarDetails
import com.infinum.course.car.checkup.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.exceptions.CarNotFoundException
import com.infinum.course.car.checkup.repository.CarRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CarService(
                   private val carRepository: CarRepository,
                   private val checkUpRepository: CheckUpRepository
) {
    fun addCar(car: CarDetails): Car {
       return carRepository.save(Car(0, LocalDateTime.now(),car.manufacturer,car.model,car.productionYear,car.vin,))
    }
    fun findCar(id:Long) = carRepository.findCarById(id)

    fun getCheckups(car:Car)=checkUpRepository.findByCar(car)

    fun findAllCars(pageable: Pageable) = carRepository.findAll(pageable)

    fun getCarDetails(id:Long): CarDetails {
        val car = findCar(id)
        if(car==null)
            throw CarNotFoundException(id)
        val checkups = getCheckups(car)
        val necessity = checkups.none { CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
        return CarDetails(car.manufacturer,car.model,car.productionYear,car.vin,checkups,necessity)



    }
}