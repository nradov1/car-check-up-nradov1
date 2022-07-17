package com.infinum.course.car.checkup

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime


class InMemoryCarCheckUpRepository(){
    private val carCheckUps = mutableMapOf<Long, CarCheckUp>()
    private val cars = mutableMapOf<Long, Car>()
    fun insertCheckUp(performedAt: LocalDateTime, car: Car,workerName:String, price:Int, carID: Long): CarCheckUp {
        val id = (carCheckUps.keys.maxOrNull() ?: 0) + 1
        carCheckUps[id] = CarCheckUp(performedAt = performedAt, car = car, workerName,price, carID = carID)
        return CarCheckUp(performedAt = performedAt, car = car, workerName,price, carID = carID)
    }

    fun findCheckUpById(id: Long): CarCheckUp {
        return carCheckUps[id] ?: throw CarCheckUpNotFoundException(id)
    }

    fun deleteCheckUpById(id: Long): CarCheckUp {
        return carCheckUps.remove(id) ?: throw CarCheckUpNotFoundException(id)
    }
    fun findAllCheckUps(): Map<Long, CarCheckUp> {
        return this.carCheckUps.toMap()
    }
    fun insertCar(manufacturer: String, model:String,productionYear: Int, vin:String): Car {
        val id = (cars.keys.maxOrNull() ?: 0) + 1
        cars[id] = Car(LocalDate.now(),manufacturer,model,productionYear,vin)
        return Car(LocalDate.now(),manufacturer,model,productionYear,vin)
    }

    fun findCarById(id: Long): Car {
        return cars[id] ?: throw CarNotFoundException(id)
    }

    fun deleteCarById(id: Long): Car {
        return cars.remove(id) ?: throw CarNotFoundException(id)
    }
    fun findAllCars(): Map<Long, Car> {
        return this.cars.toMap()
    }

}

