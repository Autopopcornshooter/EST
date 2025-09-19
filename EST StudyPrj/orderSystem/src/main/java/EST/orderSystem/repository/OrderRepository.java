package EST.orderSystem.repository;

import EST.orderSystem.domain.Order;
import EST.orderSystem.dto.ProductResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
