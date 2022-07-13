package com.infinum.course.car.checkup


import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class CarCheckUpSystem(@Qualifier("inMemoryRepo")val repo: CarCheckUpRepository){


private fun findCar(vin: String): Car {
    val checks = repo.findAll().values.filter{ CarCheckUp -> CarCheckUp.car.vin == vin}
    if(checks.isEmpty()) throw Exception("Car not found!")
    else return checks[0].car
}
fun addCheckUp(vin: String): CarCheckUp {
    val car = findCar(vin)
    repo.insert(LocalDateTime.now(),car)
    return CarCheckUp(repo.findAll().values.maxOf { CarCheckUp -> CarCheckUp.id }, LocalDateTime.now(), car)
}

fun getCheckUps(vin: String): List<CarCheckUp> {
    findCar(vin)
    return repo.findAll().values.filter { CarCheckUp -> CarCheckUp.car.vin == vin }

}

fun countCheckUps(manufacturer: String): Int =
    repo.findAll().values.count { CarCheckUp -> CarCheckUp.car.manufacturer == manufacturer }

fun isCheckUpNecessary(vin: String): Boolean =
    getCheckUps(vin).none { CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
}
