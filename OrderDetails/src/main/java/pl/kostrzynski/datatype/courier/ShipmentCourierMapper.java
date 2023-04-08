package pl.kostrzynski.datatype.courier;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShipmentCourierMapper {
    ShipmentCourier toDomain(ShipmentCourierViewModel shipmentCourierViewModel);
}

