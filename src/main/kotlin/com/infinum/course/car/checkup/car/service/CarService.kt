package com.infinum.course.car.checkup.car.service

import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.car.controller.CarDetails
import com.infinum.course.car.checkup.carmodel.entity.CarModels
import com.infinum.course.car.checkup.carmodel.repository.CarModelRepository
import com.infinum.course.car.checkup.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.exceptions.CarModelError
import com.infinum.course.car.checkup.exceptions.CarNotFoundException
import com.infinum.course.car.checkup.repository.CarRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CarService(
                   private val carRepository: CarRepository,
                   private val checkUpRepository: CheckUpRepository,
                   private val carModelRepository: CarModelRepository
) {

    fun addCar(car: CarDetails): Car {
        val car_model = carModelRepository.findByManufacturerAndModel(car.manufacturer,car.model)
            ?: throw CarModelError(car.manufacturer,car.model)
        return carRepository.save(Car(0, LocalDateTime.now(),car.productionYear,car.vin,car_model))
    }
    fun findCar(id:Long) = carRepository.findCarById(id)

    fun getCheckups(car:Car)=checkUpRepository.findByCar(car)

    fun findAllCars(pageable: Pageable) = carRepository.findAll(pageable)

    @Transactional
    fun getCarDetails(id:Long): CarDetails {
        val car = findCar(id)
        if(car==null)
            throw CarNotFoundException(id)
        val checkups = getCheckups(car)
        val necessity = checkups.none { CarCheckUp -> CarCheckUp.performedAt.plusYears(1) >= LocalDateTime.now() }
        return CarDetails(car.carModel.manufacturer,car.carModel.model,car.productionYear,car.vin,checkups,necessity)
    }
    fun update(models:List<CarModels>){
        carModelRepository.deleteAll()
        carModelRepository.saveAll(models)
    }

    fun deleteById(id:Long):Car?{
        checkUpRepository.deleteByCarId(id)
        return carRepository.deleteById(id)
    }
}