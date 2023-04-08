package pl.kostrzynski.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/order")
class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @PostMapping
    Mono<ResponseEntity<Long>> placeOrder(@RequestBody @Valid OrderViewModel orderViewModel) {

        final var order = this.orderMapper.toDomain(orderViewModel);

        return this.orderService.addOrder(order)
                .map(ResponseEntity::ok);
    }

}
