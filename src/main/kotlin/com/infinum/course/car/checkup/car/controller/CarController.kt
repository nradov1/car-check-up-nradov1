package com.infinum.course.car.checkup.car.controller

import com.infinum.course.car.checkup.car.service.CarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.data.domain.Pageable


@Controller
class CarController(
    private val carService: CarService
) {

    //getCheckups??
   @GetMapping("/car-details")
    @ResponseBody
    fun carDetails( @RequestParam("id") id: Int)= ResponseEntity(carService.getCarDetails(id.toLong()),HttpStatus.OK)


    @GetMapping("cars-paged")
    fun getAllCars(pageable: Pageable) = ResponseEntity.ok(carService.findAllCars(pageable))

    @PostMapping("/create-car")
    @ResponseBody
    fun createCar(@RequestBody car: CarDetails) = ResponseEntity(carService.addCar(car), HttpStatus.OK)


}