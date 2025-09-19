package EST.orderSystem.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private BigDecimal price;

    @Builder
    public Product(Long id, String productName, BigDecimal price){
        this.id=id;
        this.productName=productName;
        this.price=price;
    }

}
