package EST.orderSystem.dto;

import EST.orderSystem.domain.Delivery;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DeliveryResponseDto {
    private final Long id;
    private final String destination;
    private final String deliverCompany;

    @Builder
    public DeliveryResponseDto(Long id, String destination, String deliverCompany){
        this.id=id;
        this.destination=destination;
        this.deliverCompany=deliverCompany;
    }

    public static DeliveryResponseDto from(Delivery delivery){
        return DeliveryResponseDto.builder()
                .id(delivery.getId())
                .destination(delivery.getDestination())
                .deliverCompany(delivery.getDeliverCompany())
                .build();
    }
}
