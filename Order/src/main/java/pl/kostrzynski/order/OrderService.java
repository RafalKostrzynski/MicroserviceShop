package pl.kostrzynski.order;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class OrderService {

    private final OrderRepository repository;

    private final OrderMapper mapper;

    Mono<Long> addOrder(final Order order) {

        return ReactiveSecurityContextHolder.getContext()
                .map(e -> {
                    final var name = e.getAuthentication().getName();
                    order.setUsername(name);
                    return this.mapper.toEntity(order);
                })
                .flatMap(this.repository::save)
                .mapNotNull(OrderJpaEntity::getId);
    }

}
