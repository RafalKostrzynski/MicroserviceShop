package pl.kostrzynski.courier;

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
class PopulateDbWithCouriers {

    private final ShipmentCourierRepository repository;

    private final Random random = new Random();

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {

        this.repository.count()
                .filter(e -> e.equals(0L))
                .flatMapMany(e ->
                        repository.saveAll(createCouriers())
                                .then(Mono.just("The database has been populated successfully with shipment couriers."))
                )
                .switchIfEmpty(Mono.just("The database already contains shipment couriers."))
                .subscribe(log::info);
    }

    private List<ShipmentCourierJpaEntity> createCouriers() {
        final var courier1 = ShipmentCourierJpaEntity.builder()
                .courier(Courier.PP)
                .courierMargin(new BigDecimal(random.nextInt(10)))
                .build();
        final var courier2 = ShipmentCourierJpaEntity.builder()
                .courier(Courier.BAPS)
                .courierMargin(new BigDecimal(random.nextInt(10)))
                .build();
        final var courier3 = ShipmentCourierJpaEntity.builder()
                .courier(Courier.LHD)
                .courierMargin(new BigDecimal(random.nextInt(10)))
                .build();
        final var courier4 = ShipmentCourierJpaEntity.builder()
                .courier(Courier.SPS)
                .courierMargin(new BigDecimal(random.nextInt(10)))
                .build();

        return List.of(courier1, courier2, courier3, courier4);
    }

}