package pl.kostrzynski.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public Mono<BigDecimal> getPurchasePriceByNames(final List<String> productNames) {

        return this.getProductsByNames(productNames)
                .distinct()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    private Flux<Product> getProductsByNames(final List<String> productNames) {

        return this.productRepository.findByNameIn(productNames)
                .map(this.productMapper::toDomain);
    }

}
