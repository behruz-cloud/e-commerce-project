package uz.pdp.ecommerceproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommerceproject.base.BaseEntity;
import uz.pdp.ecommerceproject.util.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private Role role;
}
