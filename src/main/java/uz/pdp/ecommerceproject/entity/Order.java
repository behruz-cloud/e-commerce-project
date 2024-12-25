package uz.pdp.ecommerceproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.ecommerceproject.base.BaseEntity;
import uz.pdp.ecommerceproject.util.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private Status status;
    @CreationTimestamp
    private LocalDateTime orderDate;
    @ManyToOne
    private User user;
}
