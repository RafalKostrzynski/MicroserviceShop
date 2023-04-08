package pl.kostrzynski.order_details;

import lombok.Builder;
import lombok.Getter;
import pl.kostrzynski.datatype.courier.ShipmentCourier;
import pl.kostrzynski.datatype.region.ShipmentRegion;

import java.math.BigDecimal;

@Builder
@Getter
class OrderDetails {

    private ShipmentCourier shipmentCourier;

    private ShipmentRegion shipmentRegion;

    private BigDecimal totalPrice;

}
