package EST.orderSystem.controller;

import EST.orderSystem.dto.DeliveryResponseDto;
import EST.orderSystem.dto.OrderResponseDto;
import EST.orderSystem.dto.ProductResponseDto;
import EST.orderSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponseDto findOrderById(@PathVariable Long id){
       return orderService.orderResponseById(id);
    }

    @GetMapping("/{id}/delivery")
    public DeliveryResponseDto deliveryInOrder(@PathVariable Long id){
        return orderService.deliveryInOrder(id);
    }
    @GetMapping("/{id}/products")
    public List<ProductResponseDto> productsInOrder(@PathVariable Long id){
        return orderService.productsInOrder(id);
    }
}
