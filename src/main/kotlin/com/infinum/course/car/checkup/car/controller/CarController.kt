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
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.web.bind.annotation.*
import java.net.URI


@RequestMapping("/api/v1/car")
@Controller
class CarController(
    private val carService: CarService,
    private val resourceAssembler: CarResourceAssembler
) {

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
    @GetMapping("delete/{id}")
    @ResponseBody
    fun carDelete( @PathVariable("id") id: Long): ResponseEntity<Car> {
        val car = carService.findCar(id)
        carService.deleteById(id)
        return ResponseEntity.ok(car)
    }

    @PostMapping
    @ResponseBody
    fun createCar(@RequestBody car: CarDetails): ResponseEntity<Unit>{
        val carDto = carService.addCar(car)
        val location: URI = linkTo(methodOn(CarController::class.java).createCar(car)).toUri()
        return ResponseEntity.created(location).build()
    }


}