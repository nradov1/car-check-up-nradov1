package com.infinum.course.car.checkup


import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class CarCheckUpSystem(val repo: InMemoryCarCheckUpRepository){


fun findCar(id:Long): Car = repo.findCarById(id)
fun addCheckUp(id: Long, workerName:String, price:Int):CarCheckUp{
    val car = findCar(id)
    return repo.insertCheckUp(LocalDateTime.now(),car,workerName,price,id)
}

fun getCheckUps(id:Long): List<CarCheckUp> = findCar(id).checkUps

fun addCar(manufacturer: String, model:String,productionYear: Int, vin:String) : Car = repo.insertCar(manufacturer, model,productionYear, vin)

fun getManufacturers() : MutableSet<String>{
    var cars = repo.findAllCars().values
    var manufacturers : MutableSet<String> = mutableSetOf()
    for (car in cars){
        manufacturers.add(car.manufacturer)
    }
    return manufacturers
}

    fun countCheckUps(manufacturer: String): Int =
    repo.findAllCheckUps().values.count { CarCheckUp -> CarCheckUp.car.manufacturer == manufacturer }

fun isCheckUpNecessary(id:Long): Boolean =
    getCheckUps(id).none { CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
}


