package com.infinum.course.car.checkup.checkup.service

import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.exceptions.CarNotFoundException
import com.infinum.course.car.checkup.checkup.controller.CheckUpDetails
import com.infinum.course.car.checkup.repository.CarRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CheckUpService(
    private val checkUpRepository: CheckUpRepository,
    private val carRepository: CarRepository
) {
    fun analytics() = checkUpRepository.analytics()

    fun save(checkUp: CheckUpDetails):CarCheckUp{
        val car = carRepository.findCarById(checkUp.carID.toLong()) ?: throw CarNotFoundException(checkUp.carID.toLong())
        return checkUpRepository.save(CarCheckUp(0, LocalDateTime.now(),checkUp.workerName,checkUp.price,car))

    }
    fun getAllCheckups(pageable: Pageable) = checkUpRepository.findAll(pageable)
}