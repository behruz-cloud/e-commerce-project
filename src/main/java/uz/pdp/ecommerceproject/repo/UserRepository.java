package uz.pdp.ecommerceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerceproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByPhoneAndPassword(String phone, String password);
}