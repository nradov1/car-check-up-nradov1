package com.infinum.course.car.checkup.checkup.controller.dto

import com.infinum.course.car.checkup.car.controller.CarController
import com.infinum.course.car.checkup.checkup.controller.CheckUpController
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component

@Component
class CheckUpAssembler: RepresentationModelAssemblerSupport<CarCheckUp, CheckUpResource>(
    CheckUpController::class.java, CheckUpResource::class.java
) {
    override fun toModel(entity: CarCheckUp): CheckUpResource {
        return createModelWithId(entity.id, entity).apply {
            add(
                linkTo<CarController> {
                    carDetails(carID.toLong())
                }.withRel("cars")
            )
        }
    }

    override fun instantiateModel(entity: CarCheckUp): CheckUpResource {
        return CheckUpResource(
            id = entity.id,
            price = entity.price,
            workerName = entity.workerName,
            carID = entity.car.id.toInt()
        )
    }
}

@Relation(collectionRelation = IanaLinkRelations.ITEM_VALUE)
data class CheckUpResource(
    var id: Long=0,
    var price:Int=0,
    var carID:Int = 1,
    var workerName:String=""
) : RepresentationModel<CheckUpResource>()
