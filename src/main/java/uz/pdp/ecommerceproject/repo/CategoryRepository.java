package uz.pdp.ecommerceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerceproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}