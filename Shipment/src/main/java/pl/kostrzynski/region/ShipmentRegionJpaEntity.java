package pl.kostrzynski.region;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(name = "shipment_region")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ShipmentRegionJpaEntity implements Persistable<Long> {

    @Id
    @Column("id")
    private Long id;

    @Column("region")
    private Region region;

    @Column("shipment_margin")
    private BigDecimal shipmentMargin;

    @Transient
    private boolean newRegion;

    @Override
    @Transient
    public boolean isNew() {
        return this.newRegion || id == null;
    }

}
