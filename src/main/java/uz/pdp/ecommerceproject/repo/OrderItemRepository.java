package uz.pdp.ecommerceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerceproject.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}