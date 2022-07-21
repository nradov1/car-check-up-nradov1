package com.infinum.course.car.checkup


import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


/*      Request processing failed; nested exception is org.springframework.jdbc.BadSqlGrammarException:
        PreparedStatementCallback; bad SQL grammar [INSERT INTO cars (manufacturer,model,vin,createdOn,productionYear)
        VALUES (?,?,?,?,?)]; nested exception is org.postgresql.util.PSQLException: ERROR: relation "cars" does not exist

        Request processing failed; nested exception is org.springframework.jdbc.BadSqlGrammarException:
        PreparedStatementCallback; bad SQL grammar [SELECT * FROM cars WHERE id = ?];
        nested exception is org.postgresql.util.PSQLException: ERROR: relation "cars" does not exist
 */

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @Test
    fun test() {
        mockMvc.post("/create-car") {
            content = objectMapper.writeValueAsString(mapOf("manufacturer" to "Porsche",
                "model" to "Taycan",
                "productionYear" to "2018",
                "vin" to "89FRNRCN8"))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }

    }

    @Test
    fun test2() {
        mockMvc.get("/car-details") {
            param("id", "1")
        }
            .andExpect { status { isOk() } }
    }
}