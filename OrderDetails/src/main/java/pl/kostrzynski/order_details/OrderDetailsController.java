package pl.kostrzynski.order_details;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/order-details")
class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;
    private final OrderDetailsRequestMapper orderDetailsRequestMapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @GetMapping
    Mono<ResponseEntity<OrderDetailsViewModel>> getOrderDetails(@RequestBody @Valid OrderDetailsRequestViewModel orderDetailsRequestViewModel) {

        final var orderDetailsRequest = this.orderDetailsRequestMapper.toDomain(orderDetailsRequestViewModel);

        return this.orderDetailsService.createOrderDetails(orderDetailsRequest)
                .map(this.orderDetailsMapper::toViewModel)
                .map(ResponseEntity::ok);
    }

}
