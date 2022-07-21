package com.infinum.course.car.checkup

import com.infinum.course.car.checkup.repository.CarCheckUpRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDate
import java.time.LocalDateTime


@Controller
class CarCheckUpSystemController(private val carCheckUpRepository: CarCheckUpRepository,
        private val jdbcTemplate: NamedParameterJdbcTemplate

) {

   @GetMapping("/car-details")
    @ResponseBody
    fun carDetails( @RequestParam("id") id: Int): ResponseEntity<CarDetails> {
       val car = carCheckUpRepository.findCar(id.toLong())
       val checkups = carCheckUpRepository.getCheckups(id)
       val neccessity = checkups.none { CarCheckUp -> CarCheckUp?.performedAt?.plusYears(1)!! >= LocalDateTime.now() }
       val carDetails = CarDetails(car!!.manufacturer,car.model,car.productionYear,car.vin,checkups,neccessity)
       return ResponseEntity(carDetails,HttpStatus.OK)
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
        if(carCheckUpRepository.findCar(carCheckUp.carID.toLong())==null)
            throw CarNotFoundException(carCheckUp.carID.toLong())
        carCheckUpRepository.insertCheckUp(carCheckUp.workerName,LocalDateTime.now().toString(),carCheckUp.carID,carCheckUp.price)
        //val newCarCheckUp = carCheckUpSystem.addCheckUp(carCheckUp.carID.toLong(),carCheckUp.workerName,carCheckUp.price)
        return ResponseEntity(CarCheckUp(LocalDateTime.now(),carCheckUp.workerName,carCheckUp.price,carCheckUp.carID.toLong()),HttpStatus.OK)
    }

}