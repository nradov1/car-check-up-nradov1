package com.infinum.course.car.checkup.checkup.repository


import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.helpers.Analytics
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query


interface CheckUpRepository:JpaRepository<CarCheckUp,Long>, JpaSpecificationExecutor<CarCheckUp>{
    fun findByCar(car: Car): MutableList<CarCheckUp>

    fun findByCar(car: Car, page: Pageable): Page<CarCheckUp>

    fun save(checkUp : CarCheckUp): CarCheckUp

    //pitaj jel ok
    @Query(nativeQuery = true,value="select c.manufacturer,count(*) as checkups from check_ups b,car c where c.id=b.car_id group by c.manufacturer")
    fun analytics():List<Analytics>

}