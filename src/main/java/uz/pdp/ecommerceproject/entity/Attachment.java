package uz.pdp.ecommerceproject.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommerceproject.base.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends BaseEntity {
    private String fileName;
}
