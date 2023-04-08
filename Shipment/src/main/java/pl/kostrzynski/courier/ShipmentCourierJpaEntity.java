package pl.kostrzynski.courier;

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

@Table(name = "shipment_courier")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ShipmentCourierJpaEntity implements Persistable<Long> {

    @Id
    @Column("id")
    private Long id;

    @Column("courier")
    private Courier courier;

    @Column("courier_margin")
    private BigDecimal courierMargin;

    @Transient
    private boolean newCourier;

    @Override
    @Transient
    public boolean isNew() {
        return this.newCourier || id == null;
    }
}
