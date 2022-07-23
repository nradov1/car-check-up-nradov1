package com.infinum.course.car.checkup.controller

import com.infinum.course.car.checkup.helpers.CarDetails
import com.infinum.course.car.checkup.helpers.CarNotFoundException
import com.infinum.course.car.checkup.helpers.CheckUpDetails
import com.infinum.course.car.checkup.entity.Car
import com.infinum.course.car.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.repository.CarCheckUpRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDate
import java.time.LocalDateTime


@Controller
class CarCheckUpSystemController(private val carCheckUpRepository: CarCheckUpRepository
) {

   @GetMapping("/car-details")
    @ResponseBody
    fun carDetails( @RequestParam("id") id: Int): ResponseEntity<CarDetails> {
       val car = carCheckUpRepository.findCar(id.toLong())
       val checkups = carCheckUpRepository.getCheckups(id)
       val necessity = checkups.none { CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
       if(car==null)
           throw CarNotFoundException(id.toLong())
       else
           return ResponseEntity(CarDetails(car.manufacturer,car.model,car.productionYear,car.vin,checkups,necessity),HttpStatus.OK)
        }

    @GetMapping("/manufacturer-analytics")
    @ResponseBody
    fun analytics(): MutableList<String> = carCheckUpRepository.analytics()


    @PostMapping("/create-car")
    @ResponseBody
    fun createCar(@RequestBody car: CarDetails): ResponseEntity<Car> {
        carCheckUpRepository.insertCar(car.manufacturer,car.model,car.vin,car.productionYear,LocalDate.now().toString())
        //val newCar = carCheckUpSystem.addCar(car.manufacturer,car.model,car.productionYear,car.vin)
        return ResponseEntity(Car(LocalDate.now(),car.manufacturer,car.model,car.productionYear,car.vin), HttpStatus.OK)
    }

    @PostMapping("/create")
    @ResponseBody
    fun createCheckUps(@RequestBody carCheckUp: CheckUpDetails): ResponseEntity<CarCheckUp?>{
        if(carCheckUpRepository.findCar(CheckUpDetails.carID.toLong())==null)
            throw CarNotFoundException(CheckUpDetails.carID.toLong())
        carCheckUpRepository.insertCheckUp(
            CheckUpDetails.workerName,LocalDateTime.now().toString(),
            CheckUpDetails.carID,
            CheckUpDetails.price
        )
        return ResponseEntity(CarCheckUp(LocalDateTime.now(),
            CheckUpDetails.workerName,
            CheckUpDetails.price,
            CheckUpDetails.carID.toLong()),HttpStatus.OK)
    }

}