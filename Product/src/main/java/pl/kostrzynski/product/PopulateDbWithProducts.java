package pl.kostrzynski.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Log4j2
class PopulateDbWithProducts {

    private final ProductRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {

        this.repository.count()
                .filter(e -> e.equals(0L))
                .flatMapMany(e ->
                        repository.saveAll(createProducts())
                                .then(Mono.just("The database has been populated successfully with products."))
                )
                .switchIfEmpty(Mono.just("The database already contains products."))
                .subscribe(log::info);
    }

    private List<ProductJpaEntity> createProducts() {
        return IntStream.rangeClosed(1, 20)
                .mapToObj(e -> ProductJpaEntity.builder()
                        .quantity(e)
                        .name("product %s".formatted(e))
                        .price(new BigDecimal(e * 100))
                        .build()
                ).toList();
    }

}
