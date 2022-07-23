package com.infinum.course.car.checkup.repository

import com.infinum.course.car.checkup.entity.Car
import com.infinum.course.car.checkup.entity.CarCheckUp
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime


@Repository
class CarCheckUpRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) {
    fun insertCar(manufacturer: String,model:String,vin:String,productionYear:Int,createdOn:String) {
        jdbcTemplate.update(
            "INSERT INTO cars (manufacturer,model,vin,createdOn,productionYear) VALUES (:manufacturer,:model,:vin,:createdOn,:productionYear)",
            mapOf(
                "manufacturer" to manufacturer,
                "model" to model,
                "vin" to vin,
                "createdOn" to createdOn,
                "productionYear" to productionYear
            )

        )
    }
    fun insertCheckUp(workerName: String,performedAt:String,carID:Int,price:Int){
        jdbcTemplate.update("INSERT INTO checkUps (workerName,performedAt,carID,price) VALUES (:workerName,:performedAt,:carID,:price)",
            mapOf("workerName" to workerName,
                "price" to price,
                "performedAt" to performedAt,
                "carID" to carID)

        )
    }
    fun findCar(id:Long): Car?{
        return jdbcTemplate.queryForObject(
            "SELECT * FROM cars WHERE id = :id",
            mapOf("id" to id)
        ) { resultSet, _ ->
            Car(
                manufacturer = resultSet.getString("manufacturer"),
                model = resultSet.getString("model"),
                vin = resultSet.getString("vin"),
                createdOn = LocalDate.parse(resultSet.getString("createdOn")),
                productionYear = resultSet.getInt("productionYear")
            )
        }
    }
    fun getCheckups(id:Int):MutableList<CarCheckUp>{
        var rowMapper: RowMapper<CarCheckUp> = RowMapper<CarCheckUp> { resultSet: ResultSet, rowIndex: Int ->
            CarCheckUp(workerName = resultSet.getString("workerName"), price = resultSet.getInt("price"), performedAt = LocalDateTime.parse(resultSet.getString("performedAt")), carID = resultSet.getInt("carID").toLong())
        }
        return jdbcTemplate.query(
            "SELECT * FROM checkUps WHERE carID = :id",mapOf("id" to id),rowMapper
        )
    }
    fun analytics(): MutableList<Pair<String,Int>> {

        var rowMapper: RowMapper<Pair<String,Int>> =
            RowMapper<Pair<String,Int>> { resultSet: ResultSet, rowIndex: Int ->
                Pair(resultSet.getString("manufacturer").toString(),resultSet.getInt("checkups"))
            }

        var results = jdbcTemplate.query(
            "SELECT manufacturer, COUNT(checkUps.id) checkUps FROM cars,checkUps WHERE cars.id = carID GROUP BY manufacturer",
            rowMapper
        )

        return results
    }
}