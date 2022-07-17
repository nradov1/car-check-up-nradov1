package com.infinum.course.car.checkup

import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class CarCheckUpSystemController(private val carCheckUpSystem: CarCheckUpSystem) {
    @GetMapping("/car-details/{id}")
    fun carDetails(@PathVariable id: Long): String{
        val car = carCheckUpSystem.findCar(id)
        car.checkUps.sortByDescending { CarCheckUp -> CarCheckUp.performedAt }
        if(carCheckUpSystem.isCheckUpNecessary(id)===true)
        return car.toString() + "\n Car checkup necessary!"
        else
            return car.toString()
    }
    @GetMapping("/manufacturer-analytics")
    @ResponseBody
    fun Analytics(): String{
        var analytics : MutableMap<String,Int> = mutableMapOf()
        var manufacturers = carCheckUpSystem.getManufacturers()
        for(manufacturer in manufacturers)
            analytics[manufacturer] = carCheckUpSystem.countCheckUps(manufacturer)
        return analytics.toString()

    }

    @PostMapping("/create-car")
    @ResponseBody
    fun createCar(@RequestBody car: Car): ResponseEntity<Car> {
        val newCar = carCheckUpSystem.addCar(car.manufacturer,car.model,car.productionYear,car.vin)
        return ResponseEntity(newCar, HttpStatus.OK)
    }
    @PostMapping("/create-checkup/")
    @ResponseBody
    fun createCheckUps(@RequestBody carCheckUp: CarCheckUp): ResponseEntity<CarCheckUp> {
        val newCarCheckUp = carCheckUpSystem.addCheckUp(carCheckUp.carID,carCheckUp.workerName,carCheckUp.price)
        return ResponseEntity(newCarCheckUp, HttpStatus.OK)
    }
}