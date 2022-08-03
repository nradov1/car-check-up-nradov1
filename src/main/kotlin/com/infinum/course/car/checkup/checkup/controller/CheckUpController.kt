package com.infinum.course.car.checkup.checkup.controller

import com.infinum.course.car.checkup.car.controller.dto.CarResource
import com.infinum.course.car.checkup.checkup.controller.dto.CheckUpAssembler
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.checkup.service.CheckUpService
import com.infinum.course.car.checkup.exceptions.CarNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*


@RequestMapping("/api/v1")
@Controller
class CheckUpController(
    private val checkUpService: CheckUpService,
) {
    @GetMapping("/manufacturer-analytics")
    @ResponseBody
    fun analytics()=checkUpService.analytics()

    @PostMapping("/create-checkup")
    @ResponseBody
    fun createCheckUps(@RequestBody carCheckUp: CheckUpDetails): ResponseEntity<CarCheckUp> {
        return ResponseEntity(checkUpService.save(carCheckUp),HttpStatus.OK)
    }

    @GetMapping("/checkups/delete/{id}")
    @ResponseBody
    fun carDelete( @PathVariable("id") id: Long): ResponseEntity<Optional<CarCheckUp>> {
        val checkup = checkUpService.findById(id)
        checkUpService.deleteById(id)
        return ResponseEntity.ok(
           checkup
        )
    }
}