package pl.kostrzynski.courier;

import lombok.Data;

import java.math.BigDecimal;

@Data
class ShipmentCourier {

    private Long id;

    private Courier courier;

    private BigDecimal courierMargin;

}
