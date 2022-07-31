package com.infinum.course.car.checkup.checkup.controller

import com.infinum.course.car.checkup.checkup.controller.dto.CheckUpAssembler
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.checkup.service.CheckUpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1")
@Controller
class CheckUpController(
    private val checkUpService: CheckUpService,
    checkUpResourcesAssembler: CheckUpAssembler
) {
    @GetMapping("/manufacturer-analytics")
    @ResponseBody
    fun analytics()=checkUpService.analytics()

    @PostMapping("/create-checkup")
    @ResponseBody
    fun createCheckUps(@RequestBody carCheckUp: CheckUpDetails): ResponseEntity<CarCheckUp> {
        return ResponseEntity(checkUpService.save(carCheckUp),HttpStatus.OK)
    }
}