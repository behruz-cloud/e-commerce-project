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
public class Product extends BaseEntity {
    private String name;
    private Integer price;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Attachment attachment;
    private boolean inBasket;

    public Product(String name, Integer price, Category category, Attachment attachment) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.attachment = attachment;
    }
}
