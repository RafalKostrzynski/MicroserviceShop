package pl.kostrzynski.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/product")
class ProductController {

    private final ProductService productService;

    @GetMapping("/price")
    Mono<String> getPrice(List<String> names) {

        return this.productService.getPurchasePriceByNames(names)
                .map(BigDecimal::toString);
    }
}
