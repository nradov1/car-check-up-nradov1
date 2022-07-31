package com.infinum.course.car.checkup.car.controller.dto

import com.infinum.course.car.checkup.car.controller.CarController
import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.checkup.controller.CarCheckUpController
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component

@Component
class CarResourceAssembler:RepresentationModelAssemblerSupport<Car,CarResource>(
    CarController::class.java, CarResource::class.java
) {
    private val noPagination = Pageable.unpaged()
    private val nullAssembler = PagedResourcesAssembler<CarCheckUp>(null, null)

    override fun toModel(entity: Car): CarResource {
        return createModelWithId(entity.id, entity).apply {
            add(
                linkTo<CarCheckUpController> {
                    getAllCheckups(entity.id, noPagination, nullAssembler)
                }.withRel("checkups")
            )
        }
    }

    override fun instantiateModel(entity: Car): CarResource {
        return CarResource(entity.id, entity.carModel.manufacturer,entity.carModel.model,entity.productionYear,entity.vin)
    }
}


@Relation(collectionRelation = IanaLinkRelations.ITEM_VALUE)
data class CarResource(
    var id:Long=0L,
    var manufacturer:String="",
    var model:String="",
    var productionYear:Int=0,
    var vin:String=""
): RepresentationModel<CarResource>()