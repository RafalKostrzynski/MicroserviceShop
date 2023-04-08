package pl.kostrzynski.region;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
interface ShipmentRegionRepository extends R2dbcRepository<ShipmentRegionJpaEntity, Long> {

    Mono<ShipmentRegionJpaEntity> findShipmentRegionJpaEntitiesByRegion(Region region);

}
