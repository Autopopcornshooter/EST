package EST.orderSystem.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private String deliverCompany;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Builder
    public Delivery(Long id, String destination, String deliverCompany){
        this.id=id;
        this.destination=destination;
        this.deliverCompany=deliverCompany;
    }
}
