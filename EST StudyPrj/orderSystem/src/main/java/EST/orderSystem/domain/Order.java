package EST.orderSystem.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String ordererName;
    private String paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

   @OneToMany(mappedBy = "order")
   private List<OrderList> orderLists= new ArrayList<>();

    @Builder
    public Order(Long id,String ordererName, String paymentMethod){
        this.id=id;
        this.ordererName=ordererName;
        this.paymentMethod=paymentMethod;

    }
}
