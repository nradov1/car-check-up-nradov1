package com.infinum.course.car.checkup.checkup.repository


import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query



interface CheckUpRepository:JpaRepository<CarCheckUp,Long>, JpaSpecificationExecutor<CarCheckUp>  {
    fun findByCar(car: Car): MutableList<CarCheckUp>

    fun findByCar(car: Car, page: Pageable): Page<CarCheckUp>

    fun save(checkUp : CarCheckUp): CarCheckUp

    @Query("select manufacturer, count(*) as checkups from Car,check_ups where check_ups.car_id=car.id group by manufacturer", nativeQuery = true)
    fun analytics():MutableMap<String,Int>
}