package pl.kostrzynski.region;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class ShipmentRegionService {

    private final ShipmentRegionRepository repository;

    private final ShipmentRegionMapper mapper;

    Mono<ShipmentRegion> getShipmentRegionByRegion(final Region region) {

        return this.repository.findShipmentRegionJpaEntitiesByRegion(region)
                .map(this.mapper::toDomain)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

}
