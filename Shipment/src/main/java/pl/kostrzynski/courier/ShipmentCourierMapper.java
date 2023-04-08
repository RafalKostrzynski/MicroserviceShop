package pl.kostrzynski.courier;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ShipmentCourierMapper {

    ShipmentCourier toDomain(ShipmentCourierJpaEntity entity);

    ShipmentCourierViewModel toViewModel(ShipmentCourier shipmentCourier);
}

