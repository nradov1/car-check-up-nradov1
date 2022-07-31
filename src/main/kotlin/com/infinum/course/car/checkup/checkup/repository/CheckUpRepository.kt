package com.infinum.course.car.checkup.checkup.repository


import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface CheckUpRepository:JpaRepository<CarCheckUp,Long>{
    fun findByCar(car: Car): MutableList<CarCheckUp>

    fun findByCarIdOrderByPerformedAtDesc(car_id:Long,pageable: Pageable): Page<CarCheckUp>

    fun findByCarIdOrderByPerformedAtAsc(car_id:Long,pageable: Pageable): Page<CarCheckUp>

    fun save(checkUp : CarCheckUp): CarCheckUp

    @Query(nativeQuery = true,value="select d.manufacturer,count(*) as checkups from check_ups b,car c,car_models d where c.id=b.car_id and c.car_model_id=d.id group by d.manufacturer")
    fun analytics():List<Analytics>

}