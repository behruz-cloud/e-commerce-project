package uz.pdp.ecommerceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerceproject.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}