package com.infinum.course.car.checkup.repository

import com.infinum.course.car.checkup.car.entity.Car
import org.springframework.data.domain.Page
import org.springframework.data.repository.Repository
import org.springframework.data.domain.Pageable


interface CarRepository: Repository<Car, Long> {

    fun findCarById(id:Long): Car?

    fun save(car: Car): Car

    fun findAll(pageable: Pageable): Page<Car>


}