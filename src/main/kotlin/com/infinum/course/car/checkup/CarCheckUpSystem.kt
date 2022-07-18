package com.infinum.course.car.checkup



import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime


@Service
class CarCheckUpSystem(){
    val cars = mutableMapOf<Long, Car>(1L to Car(LocalDate.now(),"Porsche","Macan",2018,"83HRNFIG905J59GIZG"),
        2L to Car(LocalDate.parse("2022-01-02"),"Toyota","Aygo",2019,"83JDFIUFUJT5RIIG", mutableListOf<CarCheckUp>(CarCheckUp(LocalDateTime.now(),Car(LocalDate.parse("2022-01-02"),"Toyota","Aygo",2019,"83JDFIUFUJT5RIIG"),"Peter",2999,2L))),
        3L to Car(LocalDate.parse("2019-02-03"),"BMW","116d",2016,"38RDUFJTGUGZ9Z9O6KT"))
    val carCheckUps = mutableMapOf<Long, CarCheckUp>(
        1L to CarCheckUp(LocalDateTime.now(),Car(LocalDate.parse("2022-01-02"),"Toyota","Aygo",2019,"83JDFIUFUJT5RIIG"),"Peter",2999,2L)
    )



fun findCar(id:Long): Car = cars[id] ?: throw CarNotFoundException(id)
fun addCheckUp(id: Long, workerName:String, price:Int):CarCheckUp?{
    val car = findCar(id)
    val idCheckUp = (carCheckUps.keys.maxOrNull() ?: 0) + 1
    carCheckUps[idCheckUp] = CarCheckUp(performedAt = LocalDateTime.now(), car = car, workerName = workerName,price=price, carID = id)
    cars[id]?.checkUps?.add(CarCheckUp(performedAt = LocalDateTime.now(), car = car, workerName = workerName,price=price, carID = id))
    return CarCheckUp(performedAt = LocalDateTime.now(), car = car, workerName = workerName,price=price, carID = id)
}

fun getCheckUps(id:Long): List<CarCheckUp> = findCar(id).checkUps

fun addCar(manufacturer: String, model:String,productionYear: Int, vin:String) : Car {
    val id = (cars.keys.maxOrNull() ?: 0) + 1
    cars[id] = Car(LocalDate.now(),manufacturer,model,productionYear,vin)
    return Car(LocalDate.now(),manufacturer,model,productionYear,vin)
}

fun getManufacturers() : MutableSet<String>{
    var cars = this.cars.values
    var manufacturers : MutableSet<String> = mutableSetOf()
    for (car in cars){
        manufacturers.add(car.manufacturer)
    }
    return manufacturers
}

    fun countCheckUps(manufacturer: String): Int =
    this.carCheckUps.values.count { CarCheckUp -> CarCheckUp.car.manufacturer == manufacturer }

fun isCheckUpNecessary(id:Long): Boolean =
    getCheckUps(id).none { CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
}



