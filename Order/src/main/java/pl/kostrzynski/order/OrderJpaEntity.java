package pl.kostrzynski.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(name = "orders")
@Getter
@Setter
class OrderJpaEntity implements Persistable<Long> {

    @Id
    @Column("id")
    private Long id;

    @Column("shipment_courier_name")
    private String shipmentCourierName;

    @Column("shipment_region")
    private String shipmentRegion;

    @Column("username")
    private String username;

    @Column("total_price")
    private BigDecimal totalPrice;

    @Transient
    private boolean newOrder;

    @Override
    @Transient
    public boolean isNew() {
        return this.newOrder || id == null;
    }
}
