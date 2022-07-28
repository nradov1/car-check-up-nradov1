package com.infinum.course.car.checkup.checkup.repository


import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface CheckUpRepository:JpaRepository<CarCheckUp,Long>{
    fun findByCar(car: Car): MutableList<CarCheckUp>

    fun save(checkUp : CarCheckUp): CarCheckUp

    @Query(nativeQuery = true,value="select d.manufacturer,count(*) as checkups from check_ups b,car c,car_models d where c.id=b.car_id and c.car_model_id=d.id group by d.manufacturer")
    fun analytics():List<Analytics>

}