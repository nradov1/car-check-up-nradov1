package com.infinum.course.car.checkup.carmodel.repository

import com.infinum.course.car.checkup.carmodel.entity.CarModels
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CarModelRepository:JpaRepository<CarModels,Long>, CrudRepository<CarModels,Long> {


    @Query("select * from car_models where manufacturer=:manufacturer and model=:model", nativeQuery = true)
    fun findByManufacturerAndModel(manufacturer: String, model:String):CarModels?
}