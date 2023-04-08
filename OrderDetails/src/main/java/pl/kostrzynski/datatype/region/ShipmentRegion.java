package pl.kostrzynski.datatype.region;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShipmentRegion {

    private Long id;
    private Region region;
    private BigDecimal shipmentMargin;

}
