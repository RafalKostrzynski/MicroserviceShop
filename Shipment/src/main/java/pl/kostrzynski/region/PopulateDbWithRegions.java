package pl.kostrzynski.region;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
class PopulateDbWithRegions {

    private final ShipmentRegionRepository repository;

    private final Random random = new Random();

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {

        this.repository.count()
                .filter(e -> e.equals(0L))
                .flatMapMany(e ->
                        repository.saveAll(createRegions())
                                .then(Mono.just("The database has been populated successfully with shipment regions."))
                )
                .switchIfEmpty(Mono.just("The database already contains shipment regions."))
                .subscribe(log::info);
    }

    private List<ShipmentRegionJpaEntity> createRegions() {
        final var region1 = ShipmentRegionJpaEntity.builder()
                .region(Region.EUROPE)
                .shipmentMargin(new BigDecimal(random.nextInt(50)))
                .build();
        final var region2 = ShipmentRegionJpaEntity.builder()
                .region(Region.AFRICA)
                .shipmentMargin(new BigDecimal(random.nextInt(50)))
                .build();
        final var region3 = ShipmentRegionJpaEntity.builder()
                .region(Region.NORTH_AMERICA)
                .shipmentMargin(new BigDecimal(random.nextInt(50)))
                .build();
        final var region4 = ShipmentRegionJpaEntity.builder()
                .region(Region.SOUTH_AMERICA)
                .shipmentMargin(new BigDecimal(random.nextInt(50)))
                .build();
        final var region5 = ShipmentRegionJpaEntity.builder()
                .region(Region.ASIA)
                .shipmentMargin(new BigDecimal(random.nextInt(50)))
                .build();

        return List.of(
                region1, region2, region3, region4, region5
        );
    }

}