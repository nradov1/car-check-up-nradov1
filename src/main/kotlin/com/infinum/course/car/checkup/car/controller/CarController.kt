package com.infinum.course.car.checkup.car.controller

import com.infinum.course.car.checkup.helpers.CarDetails
import com.infinum.course.car.checkup.helpers.CarNotFoundException

import com.infinum.course.car.checkup.car.service.CarSystem
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime


@Controller
class CarController(
    private val carService: CarSystem
) {

    //getCheckups??
   @GetMapping("/car-details")
    @ResponseBody
    fun carDetails( @RequestParam("id") id: Int): ResponseEntity<CarDetails> {
       val car = carService.findCar(id.toLong())
        if(car==null)
            throw CarNotFoundException(id.toLong())
       val checkups = carService.getCheckups(car)
       val necessity = checkups.none { CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
           return ResponseEntity(CarDetails(car.manufacturer,car.model,car.productionYear,car.vin,checkups,necessity),HttpStatus.OK)
        }

    @GetMapping("cars-paged")
    fun getAllCars(pageable: Pageable) = ResponseEntity.ok(carService.findAllCars(pageable))

    @PostMapping("/create-car")
    @ResponseBody
    fun createCar(@RequestBody car: CarDetails) = ResponseEntity(carService.addCar(car), HttpStatus.OK)


}