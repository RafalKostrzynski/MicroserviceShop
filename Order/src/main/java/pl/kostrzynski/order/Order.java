package pl.kostrzynski.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
class Order {

    private Long id;

    private String shipmentCourierName;

    private String shipmentRegion;

    private String username;

    private BigDecimal totalPrice;
}
