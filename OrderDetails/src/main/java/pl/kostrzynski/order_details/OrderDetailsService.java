package pl.kostrzynski.order_details;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.kostrzynski.datatype.courier.ShipmentCourier;
import pl.kostrzynski.datatype.courier.ShipmentCourierMapper;
import pl.kostrzynski.datatype.courier.ShipmentCourierViewModel;
import pl.kostrzynski.datatype.region.ShipmentRegion;
import pl.kostrzynski.datatype.region.ShipmentRegionMapper;
import pl.kostrzynski.datatype.region.ShipmentRegionViewModel;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static pl.kostrzynski.config.HttpUtils.BEARER;
import static pl.kostrzynski.config.HttpUtils.HTTPS;
import static pl.kostrzynski.config.HttpUtils.PRODUCT_PRICE_PATH;
import static pl.kostrzynski.config.HttpUtils.PRODUCT_SERVICE;
import static pl.kostrzynski.config.HttpUtils.SHIPMENT_COURIER_PATH;
import static pl.kostrzynski.config.HttpUtils.SHIPMENT_REGION_PATH;
import static pl.kostrzynski.config.HttpUtils.SHIPMENT_SERVICE;

@Service
@RequiredArgsConstructor
class OrderDetailsService {

    private final WebClient.Builder webClientBuilder;

    private final ShipmentCourierMapper shipmentCourierMapper;
    private final ShipmentRegionMapper shipmentRegionMapper;

    Mono<OrderDetails> createOrderDetails(final OrderDetailsRequest orderDetailsRequest) {

        final var webclient = this.webClientBuilder.build();

        return ReactiveSecurityContextHolder.getContext()
                .flatMap(e -> {
                    final var token = String.valueOf(e.getAuthentication().getCredentials());
                    return Mono.zip(
                            getCourierMono(orderDetailsRequest, webclient, token),
                            getRegionMono(orderDetailsRequest, webclient, token),
                            getPriceMono(orderDetailsRequest, webclient, token));
                })
                .map(tuple3 ->
                        OrderDetails.builder()
                                .shipmentCourier(tuple3.getT1())
                                .shipmentRegion(tuple3.getT2())
                                .totalPrice(
                                        tuple3.getT3()
                                                .add(tuple3.getT1().getCourierMargin())
                                                .add(tuple3.getT2().getShipmentMargin())
                                ).build()
                );
    }

    private Mono<ShipmentCourier> getCourierMono(
            final OrderDetailsRequest orderDetailsRequest,
            final WebClient webclient,
            final String token) {

        return webclient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(HTTPS)
                        .host(SHIPMENT_SERVICE)
                        .path(SHIPMENT_COURIER_PATH)
                        .queryParam("courier", orderDetailsRequest.courier())
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, BEARER + token)
                .retrieve()
                .bodyToMono(ShipmentCourierViewModel.class)
                .map(this.shipmentCourierMapper::toDomain);
    }

    private Mono<ShipmentRegion> getRegionMono(
            final OrderDetailsRequest orderDetailsRequest,
            final WebClient webclient,
            final String token) {

        return webclient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(HTTPS)
                        .host(SHIPMENT_SERVICE)
                        .path(SHIPMENT_REGION_PATH)
                        .queryParam("region", orderDetailsRequest.region())
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, BEARER + token)
                .retrieve()
                .bodyToMono(ShipmentRegionViewModel.class)
                .map(this.shipmentRegionMapper::toDomain);
    }

    private Mono<BigDecimal> getPriceMono(
            final OrderDetailsRequest orderDetailsRequest,
            final WebClient webclient,
            final String token) {

        return webclient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(HTTPS)
                        .host(PRODUCT_SERVICE)
                        .path(PRODUCT_PRICE_PATH)
                        .queryParam("names", orderDetailsRequest.products())
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, BEARER + token)
                .retrieve()
                .bodyToMono(String.class)
                .map(BigDecimal::new);
    }
}
