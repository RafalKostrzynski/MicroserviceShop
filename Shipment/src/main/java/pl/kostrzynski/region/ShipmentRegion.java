package pl.kostrzynski.region;

import lombok.Data;

import java.math.BigDecimal;

@Data
class ShipmentRegion {

    private Long id;
    private Region region;
    private BigDecimal shipmentMargin;

}
