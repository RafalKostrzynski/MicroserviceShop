package pl.kostrzynski.datatype.region;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShipmentRegionMapper {
    ShipmentRegion toDomain(ShipmentRegionViewModel shipmentRegionViewModel);
}
