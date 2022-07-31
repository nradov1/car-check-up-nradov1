package com.infinum.course.car.checkup.checkup.controller

import com.infinum.course.car.checkup.checkup.controller.dto.CheckUpAssembler
import com.infinum.course.car.checkup.checkup.controller.dto.CheckUpResource
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.checkup.service.CheckUpService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/car/{carId}/checkup")
class CarCheckUpController(private val checkUpService: CheckUpService,
                           private val checkUpResourceAssembler: CheckUpAssembler
) {
    @GetMapping
    @ResponseBody
    fun getAllCheckups(@PathVariable("carId") carId: Long,
                       pageable: Pageable,
                       pagedResourcesAssembler: PagedResourcesAssembler<CarCheckUp>
    ): ResponseEntity<PagedModel<CheckUpResource>> {
        return ResponseEntity.ok(
            pagedResourcesAssembler.toModel(
                checkUpService.findByCarId(carId, pageable),
                checkUpResourceAssembler
            )
        )
    }
    @GetMapping("/asc")
    @ResponseBody
    fun getAllCheckupsAsc(@PathVariable("carId") carId: Long,
                       pageable: Pageable,
                       pagedResourcesAssembler: PagedResourcesAssembler<CarCheckUp>
    ): ResponseEntity<PagedModel<CheckUpResource>> {
        return ResponseEntity.ok(
            pagedResourcesAssembler.toModel(
                checkUpService.findByCarIdAsc(carId, pageable),
                checkUpResourceAssembler
            )
        )
    }

}