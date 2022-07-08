package com.infinum.course.car.checkup

import java.time.LocalDateTime

class Car constructor (manufacturer: String, model:String, vin:String){
    private val manufacturer: String
    private val model: String
    private val vin: String

    init {
        this.manufacturer = manufacturer
        this.vin = vin
        this.model = model
    }
    fun getVin() = this.vin
    fun getManufacturer() = this.manufacturer

}

class CarCheckUp constructor(performedAt: LocalDateTime, car: Car){
    private val performedAt:LocalDateTime
    private val car: Car

    init {
        this.performedAt = performedAt
        this.car = car
    }
    fun getCar() = this.car
    fun getDate() = this.performedAt
    override fun toString():String{
        return "Check up performed on: " + this.performedAt
    }
}

object CarCheckUpSystem{
    private val firstCar:Car = Car("Toyota","Aygo","F73HF7R39DJ8L49G6")
    private val secondCar:Car = Car("Peugeot","308","74F7J38038575HG74")
    private val thirdCar:Car = Car(vin = "3G6F8H7225F6027G5", model = "Macan", manufacturer = "Porsche")
    private val fourthCar:Car = Car(model = "Giulia", manufacturer = "Alfa Romeo", vin = "7F6354G8F037G65K2")
    private var checkUps: MutableList<CarCheckUp>

    init {
        checkUps = mutableListOf(
            CarCheckUp(performedAt = LocalDateTime.parse("2022-07-07T11:34:00"),fourthCar) ,
            CarCheckUp(performedAt = LocalDateTime.parse("2019-12-14T14:54:00"),thirdCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2022-06-22T10:53:02"),secondCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2020-03-18T19:05:00"),firstCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2021-11-01T12:30:00"),fourthCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2022-01-15T17:00:00"),thirdCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2020-05-13T13:30:00"),secondCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2021-05-23T16:00:00"),firstCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2017-03-21T08:03:00"),fourthCar),
            CarCheckUp(performedAt = LocalDateTime.parse("2021-05-19T13:30:00"),thirdCar)
        )
    }

    private fun findVin(vin: String):Car{
        return when (vin) {
            firstCar.getVin() -> firstCar
            secondCar.getVin() -> secondCar
            thirdCar.getVin() -> thirdCar
            fourthCar.getVin() -> fourthCar
            else -> throw Exception("No such car in database")
        }

    }
    fun addCheckUp(vin: String):CarCheckUp{
        val car = findVin(vin)
        val checkUp = CarCheckUp(LocalDateTime.now(),car)
        checkUps.add(checkUp)
        return checkUp
    }
    fun getCheckUps(vin: String):List<CarCheckUp>{
        findVin(vin)
        return checkUps.filter { CarCheckUp ->  CarCheckUp.getCar().getVin() === vin}
    }
    fun countCheckUps(manufacturer: String):Int{
        return checkUps.count { CarCheckUp -> CarCheckUp.getCar().getManufacturer() === manufacturer }
    }
    fun isCheckUpNecessary(vin: String):Boolean{
        val list = getCheckUps(vin)
        var min = LocalDateTime.MIN
        for (checkUp in list)
            if(checkUp.getDate() > min)
                min = checkUp.getDate()
        if(min.plusYears(1) < LocalDateTime.now())
            return true
        return false
    }
}
fun main() {
    //println( CarCheckUpSystem.addCheckUp("T98UITJGROE4WIG8UJT4")) //exception
    println( CarCheckUpSystem.addCheckUp("F73HF7R39DJ8L49G6"))
    println( CarCheckUpSystem.countCheckUps("Toyota"))
    println( CarCheckUpSystem.countCheckUps("Mercedes-Benz"))
    //println(CarCheckUpSystem.isCheckUpNecessary("T98UITJGROE4WIG8UJT4")) //exception
    println(CarCheckUpSystem.isCheckUpNecessary("F73HF7R39DJ8L49G6"))
    println(CarCheckUpSystem.getCheckUps("3G6F8H7225F6027G5"))

}