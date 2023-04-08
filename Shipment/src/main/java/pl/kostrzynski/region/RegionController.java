package pl.kostrzynski.region;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/shipment/region")
class RegionController {

    private final ShipmentRegionService shipmentRegionService;

    private final ShipmentRegionMapper mapper;

    @GetMapping
    Mono<ResponseEntity<ShipmentRegionViewModel>> getShipmentRegion(@RequestParam final String region) {

        return this.shipmentRegionService.getShipmentRegionByRegion(Region.valueOf(region.toUpperCase()))
                .map(this.mapper::toViewModel)
                .map(ResponseEntity::ok);
    }
}
