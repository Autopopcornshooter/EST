package EST.orderSystem.dto;

import EST.orderSystem.domain.Order;
import EST.orderSystem.repository.OrderRepository;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private final Long id;
    private final String ordererName;
    private final String paymentMethod;

    @Builder
    public OrderResponseDto(Long id, String ordererName, String paymentMethod){
        this.id=id;
        this.ordererName=ordererName;
        this.paymentMethod=paymentMethod;
    }

    public OrderResponseDto from(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .ordererName(order.getOrdererName())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }
}
