package pl.kostrzynski.product;

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

@Table(name = "product")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ProductJpaEntity implements Persistable<Long> {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("quantity")
    private Integer quantity;

    @Column("price")
    private BigDecimal price;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null;
    }

}
