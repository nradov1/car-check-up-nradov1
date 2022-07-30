package com.infinum.course.car.checkup.car.service

import com.fasterxml.jackson.annotation.JsonProperty

data class CarModelResponse(
    @JsonProperty("cars") val cars:List<ManufacturerModels>
)
data class ManufacturerModels(
    @JsonProperty("manufacturer") val manufacturer:String,
    @JsonProperty("models") val models:List<String>,

)