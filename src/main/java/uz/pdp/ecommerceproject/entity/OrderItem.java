package uz.pdp.ecommerceproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommerceproject.base.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends BaseEntity {
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private Integer amount;
}
