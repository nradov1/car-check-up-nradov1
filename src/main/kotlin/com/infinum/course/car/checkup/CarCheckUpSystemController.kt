package com.infinum.course.car.checkup

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class CarCheckUpSystemController(private val carCheckUpSystem: CarCheckUpSystem) {
    @GetMapping("/car-details/{id}")
    @ResponseBody
    fun carDetails(@PathVariable id: Long): ResponseEntity<CarDetails> {
        val car = carCheckUpSystem.findCar(id)
        val list = carCheckUpSystem.getCheckUps(id)
        list.sortByDescending { CarCheckUp -> CarCheckUp?.performedAt }
        val carDetails = CarDetails(car.manufacturer,car.model,car.productionYear,car.vin,list)
        if(carCheckUpSystem.isCheckUpNecessary(id))
        carDetails.checkUpNeccessity = true
        return ResponseEntity(carDetails,HttpStatus.OK)
    }
    @GetMapping("/manufacturer-analytics")
    @ResponseBody
    fun analytics(): MutableMap<String,Int>{
        var analytics : MutableMap<String,Int> = mutableMapOf()
        var manufacturers = carCheckUpSystem.getManufacturers()
        for(manufacturer in manufacturers)
            analytics[manufacturer] = carCheckUpSystem.countCheckUps(manufacturer)
        return analytics

    }

    @PostMapping("/create-car")
    @ResponseBody
    fun createCar(@RequestBody car: CarDetails): ResponseEntity<Car> {
        val newCar = carCheckUpSystem.addCar(car.manufacturer,car.model,car.productionYear,car.vin)
        return ResponseEntity(newCar, HttpStatus.OK)
    }


    @PostMapping("/create")
    @ResponseBody
    fun createCheckUps(@RequestBody carCheckUp: CheckUpDetails): ResponseEntity<CarCheckUp?>{
        val newCarCheckUp = carCheckUpSystem.addCheckUp(carCheckUp.carID.toLong(),carCheckUp.workerName,carCheckUp.price)
        return ResponseEntity(newCarCheckUp,HttpStatus.OK)
    }
}