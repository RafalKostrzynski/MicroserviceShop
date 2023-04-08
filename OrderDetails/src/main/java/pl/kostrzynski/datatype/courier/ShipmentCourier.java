package pl.kostrzynski.datatype.courier;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShipmentCourier {

    private Long id;

    private Courier courier;

    private BigDecimal courierMargin;

}
