package uz.pdp.ecommerceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerceproject.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}