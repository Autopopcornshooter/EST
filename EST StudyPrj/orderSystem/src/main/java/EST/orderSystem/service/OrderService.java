package EST.orderSystem.service;

import EST.orderSystem.domain.Delivery;
import EST.orderSystem.domain.Order;
import EST.orderSystem.dto.DeliveryResponseDto;
import EST.orderSystem.dto.OrderResponseDto;
import EST.orderSystem.dto.ProductResponseDto;
import EST.orderSystem.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final EntityManager em;

    public OrderResponseDto orderResponseById(Long id){
        Order order=em.getReference(Order.class, id);
        return OrderResponseDto.builder()
                .ordererName(order.getOrdererName())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }

    public DeliveryResponseDto deliveryInOrder(Long id){
            Order order=orderRepository.findById(id)
                    .orElseThrow(()->new IllegalArgumentException("해당 주문이 존재하지 않습니다: "+id));
            return DeliveryResponseDto.from(order.getDelivery());
    }
    public List<ProductResponseDto> productsInOrder(Long id){
        Order order=orderRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 주문이 존재하지 않습니다: "+id));

        return order.getOrderLists()
                .stream()
                .map(entity->ProductResponseDto.from(entity.getProduct()))
                .toList();
    }



}
