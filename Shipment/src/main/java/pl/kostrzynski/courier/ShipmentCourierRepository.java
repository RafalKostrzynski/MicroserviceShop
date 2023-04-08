package pl.kostrzynski.courier;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
interface ShipmentCourierRepository extends R2dbcRepository<ShipmentCourierJpaEntity, Long> {

    Mono<ShipmentCourierJpaEntity> findByCourier(Courier courier);
}
