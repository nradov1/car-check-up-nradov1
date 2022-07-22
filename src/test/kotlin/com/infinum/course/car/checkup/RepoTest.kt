package com.infinum.course.car.checkup

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.test.annotation.Commit
import java.time.LocalDate

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
class RepositoryTest {

    @Autowired
    lateinit var jdbcTemplate: NamedParameterJdbcTemplate


    fun setUp() {
        jdbcTemplate.update(
            "INSERT INTO cars (manufacturer,model,productionYear,vin,createdOn) VALUES (:manufacturer,:model,:productionYear,:vin,:createdOn)",
            mapOf("manufacturer" to "Porsche",
                "model" to "Taycan",
                "productionYear" to 2018,
                "vin" to "T4B9THB05ZNN",
                "createdOn" to LocalDate.now().toString()
                )
        )
    }

    @Test
    fun test() {
        setUp()
        Assertions.assertThat(
            jdbcTemplate.queryForObject(
                "SELECT manufacturer FROM cars WHERE model = :name",
                mapOf("name" to "Taycan"),
                String::class.java
            )
        ).isEqualTo("Porsche")
    }

    @Test
    fun test2() {
        Assertions.assertThat(
            jdbcTemplate.queryForObject(
                "SELECT count(*) FROM cars WHERE vin = :vin",
                mapOf("vin" to "T4B9THB05ZNN"),
                Int::class.java
            )
        ).isEqualTo(1)
    }
}