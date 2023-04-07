package pl.kostrzynski.product;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
interface ProductRepository extends R2dbcRepository<ProductJpaEntity, Long> {

    Flux<ProductJpaEntity> findByNameIn(List<String> names);

}
