package pl.kostrzynski.product;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ProductMapper {

    Product toDomain(ProductJpaEntity entity);

}
