package EST.orderSystem.dto;

import EST.orderSystem.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponseDto {
    private final Long id;
    private final String productName;
    private final BigDecimal price;

    @Builder
    public ProductResponseDto(Long id, String productName, BigDecimal price){
        this.id=id;
        this.productName=productName;
        this.price=price;
    }

    public static ProductResponseDto from(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }
}
