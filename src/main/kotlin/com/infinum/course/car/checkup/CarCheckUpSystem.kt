package com.infinum.course.car.checkup

import java.time.LocalDateTime

object CarCheckUpSystem {
    private var cars: List<Car> = listOf(
        Car("Toyota", "Aygo", "F73HF7R39DJ8L49G6"),
        Car("Peugeot", "308", "74F7J38038575HG74"),
        Car(vin = "3G6F8H7225F6027G5", model = "Macan", manufacturer = "Porsche"),
        Car(model = "Giulia", manufacturer = "Alfa Romeo", vin = "7F6354G8F037G65K2")
    )
    private var checkUps: MutableList<CarCheckUp> = mutableListOf(
        CarCheckUp(LocalDateTime.parse("2022-07-07T11:34:00"), cars[3]),
        CarCheckUp(LocalDateTime.parse("2019-12-14T14:54:00"), cars[2]),
        CarCheckUp(LocalDateTime.parse("2022-06-22T10:53:02"), cars[1]),
        CarCheckUp(LocalDateTime.parse("2020-03-18T19:05:00"), cars[0]),
        CarCheckUp(LocalDateTime.parse("2021-11-01T12:30:00"), cars[3]),
        CarCheckUp(LocalDateTime.parse("2022-01-15T17:00:00"), cars[2]),
        CarCheckUp(LocalDateTime.parse("2020-05-13T13:30:00"), cars[1]),
        CarCheckUp(LocalDateTime.parse("2021-05-23T16:00:00"), cars[0]),
        CarCheckUp(LocalDateTime.parse("2017-03-21T08:03:00"), cars[3]),
        CarCheckUp(LocalDateTime.parse("2021-05-19T13:30:00"), cars[2])
    )

    private fun findCar(vin: String):Car{
        val list = cars.filter { Car -> Car.vin === vin  }
        if(list.isEmpty()) throw Exception("No matching cars!")
        else
            return list[0]
    }

    fun addCheckUp(vin: String): CarCheckUp {
        val car = findCar(vin)
        val checkUp = CarCheckUp(LocalDateTime.now(), car)
        checkUps.add(checkUp)
        return checkUp
    }

    fun getCheckUps(vin: String): List<CarCheckUp> {
        findCar(vin)
        return checkUps.filter { CarCheckUp -> CarCheckUp.car.vin === vin }

    }

    fun countCheckUps(manufacturer: String): Int =
        checkUps.count { CarCheckUp -> CarCheckUp.car.manufacturer === manufacturer }

    fun isCheckUpNecessary(vin: String): Boolean =
        getCheckUps(vin).none{ CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
}