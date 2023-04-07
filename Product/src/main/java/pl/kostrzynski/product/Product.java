package pl.kostrzynski.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long id;

    private String name;

    private Integer quantity;

    private BigDecimal price;

}
