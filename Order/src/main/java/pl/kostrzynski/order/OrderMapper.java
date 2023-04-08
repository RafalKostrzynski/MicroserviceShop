package pl.kostrzynski.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface OrderMapper {

    @Mapping(target = "username", ignore = true)
    @Mapping(target = "id", ignore = true)
    Order toDomain(OrderViewModel entity);

    OrderJpaEntity toEntity(Order order);

}
