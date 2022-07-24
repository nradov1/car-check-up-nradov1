package com.infinum.course.car.checkup.checkup.service

import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.helpers.CarNotFoundException
import com.infinum.course.car.checkup.helpers.CheckUpDetails
import com.infinum.course.car.checkup.repository.CarRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CheckUpSystem(
    private val checkUpRepository: CheckUpRepository,
    private val carRepository: CarRepository
) {
    fun analytics() = checkUpRepository.analytics()

    fun save(checkUp:CheckUpDetails):CarCheckUp{
        val car = carRepository.findCarById(checkUp.carID.toLong()) ?: throw CarNotFoundException(checkUp.carID.toLong())
        return checkUpRepository.save(CarCheckUp(0, LocalDateTime.now(),checkUp.workerName,checkUp.price,car))

    }
    fun getAllCheckups(pageable: Pageable, id:Long): Page<CarCheckUp> {
        val car = carRepository.findCarById(id) ?: throw CarNotFoundException(id)
        return checkUpRepository.findByCar(car,pageable)
    }
}