package com.infinum.course.car.checkup.carmodel.service

import com.infinum.course.car.checkup.car.service.CarModelResponse
import com.infinum.course.car.checkup.carmodel.entity.CarModels
import com.infinum.course.car.checkup.carmodel.repository.CarModelRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.*

@Service
class CarModelService(
    private val carModelRepository: CarModelRepository,
    private val restTemplate: RestTemplate,
    @Value("\${model-data.base-url}") private val baseUrl: String
) {


    fun update(){
        val map = getModels()
        val list:MutableList<CarModels> = mutableListOf()
        for(manmod in map)
            for(model in manmod.value)
                list.add(CarModels(UUID.randomUUID(),manmod.key,model))
        println(list)
        val list2:MutableList<CarModels> = mutableListOf()
        list.forEach { CarModels -> if(carModelRepository.findByManufacturerAndModel(CarModels.manufacturer,CarModels.model)==null) list2.add(CarModels) }
        println(list2)
        carModelRepository.saveAll(list2)
    }

    fun getModels(): MutableMap<String,List<String>> {
        val map:MutableMap<String,List<String>> = mutableMapOf()
        restTemplate
            .getForObject<CarModelResponse>(baseUrl)
            .cars
            .forEach({ManufacturerModels -> map.set(ManufacturerModels.manufacturer,ManufacturerModels.models)})
        return map
    }
}
