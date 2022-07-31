package com.infinum.course.car.checkup.car.controller

import com.infinum.course.car.checkup.car.controller.dto.CarResource
import com.infinum.course.car.checkup.car.controller.dto.CarResourceAssembler
import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.car.service.CarService
import com.infinum.course.car.checkup.exceptions.CarNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.PagedModel
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RequestMapping("/api/v1/car")
@Controller
class CarController(
    private val carService: CarService,
    private val resourceAssembler: CarResourceAssembler
) {

    //getCheckups??
   @GetMapping("/{id}")
    @ResponseBody
    fun carDetails( @PathVariable("id") id: Long): ResponseEntity<CarResource> {
        return ResponseEntity.ok(
            resourceAssembler.toModel(carService.findCar(id)?:throw  CarNotFoundException(id))
        )
    }


    @GetMapping("/paged")
    fun getAllCars(pageable: Pageable,pagedResourcesAssembler: PagedResourcesAssembler<Car>): ResponseEntity<PagedModel<CarResource>> {
        return ResponseEntity.ok(
            pagedResourcesAssembler.toModel(
                carService.findAllCars(pageable),
                resourceAssembler
            )
        )
    }

    @PostMapping
    @ResponseBody
    fun createCar(@RequestBody car: CarDetails): ResponseEntity<Unit>{
        val carDto = carService.addCar(car)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(mapOf("id" to carDto.id))
            .toUri()
        return ResponseEntity.created(location).build()
    }


}