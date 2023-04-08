package pl.kostrzynski.courier;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/shipment/courier")
class CourierController {

    private final ShipmentCourierService courierService;

    private final ShipmentCourierMapper mapper;

    @GetMapping
    Mono<ShipmentCourierViewModel> getShipmentCourier(@RequestParam final String courier) {

        return this.courierService.getShipmentCourierByCourier(Courier.valueOf(courier.toUpperCase()))
                .map(this.mapper::toViewModel);
    }

}
