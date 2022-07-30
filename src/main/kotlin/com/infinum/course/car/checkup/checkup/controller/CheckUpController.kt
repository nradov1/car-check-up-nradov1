package com.infinum.course.car.checkup.checkup.controller

import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.checkup.service.CheckUpService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class CheckUpController(
    private val checkUpService: CheckUpService
) {
    @GetMapping("/manufacturer-analytics")
    @ResponseBody
    fun analytics()=checkUpService.analytics()



    @GetMapping("/checkups/paged")
    @ResponseBody
    fun getAllCheckups(pageable: Pageable)=checkUpService.getAllCheckups(pageable)

    @PostMapping("/create")
    @ResponseBody
    fun createCheckUps(@RequestBody carCheckUp: CheckUpDetails): ResponseEntity<CarCheckUp?> {
        return ResponseEntity(checkUpService.save(carCheckUp),HttpStatus.OK)
    }



}