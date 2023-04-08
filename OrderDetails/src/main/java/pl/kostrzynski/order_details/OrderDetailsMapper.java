package pl.kostrzynski.order_details;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface OrderDetailsMapper {

    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "shipmentRegion", source = "shipmentRegion.region")
    @Mapping(target = "shipmentCourierName", source = "shipmentCourier.courier")
    OrderDetailsViewModel toViewModel(OrderDetails orderDetails);

}
