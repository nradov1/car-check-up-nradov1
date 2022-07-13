package com.infinum.course.car.checkup

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Qualifier("inMemoryRepo")
class InMemoryCarCheckUpRepository(val dataSource: DataSource) : CarCheckUpRepository{
    private val carCheckUpMap = mutableMapOf<Long, CarCheckUp>()
    override fun insert(performedAt: LocalDateTime, car: Car): Long {
        val id = (carCheckUpMap.keys.maxOrNull() ?: 0) + 1
        carCheckUpMap[id] = CarCheckUp(id = id, performedAt = performedAt, car = car)
        return id
    }

    override fun findById(id: Long): CarCheckUp {
        return carCheckUpMap[id] ?: throw CarCheckUpNotFoundException(id)
    }

    override fun deleteById(id: Long): CarCheckUp {
        return carCheckUpMap.remove(id) ?: throw CarCheckUpNotFoundException(id)
    }
    override fun findAll(): Map<Long, CarCheckUp> {
        return this.carCheckUpMap.toMap()
    }

    init {
        println("dbName: " + dataSource.dbName + ", username: " + dataSource.username + ", password: " + dataSource.password)
    }
}