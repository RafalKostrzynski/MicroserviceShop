package pl.kostrzynski.order;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
interface OrderRepository extends R2dbcRepository<OrderJpaEntity, Long> {

    Mono<OrderJpaEntity> findById(Long id);
}
