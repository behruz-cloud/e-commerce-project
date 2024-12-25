package uz.pdp.ecommerceproject.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerceapp.entity.Category;
import uz.pdp.ecommerceapp.repo.CategoryRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runnable implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String update;

    @Override
    public void run(String... args) throws Exception {
        if (update.equals("create")) {
            Category category1 = new Category("SMARTFONLAR");
            Category category2 = new Category("NOUTBUKLAR");
            Category category3 = new Category("MAISHIY TEXNIKALAR");
            Category category4 = new Category("SOATLAR");
            Category category5 = new Category("PLANSHETLAR");
            categoryRepository.saveAll(List.of(category1, category2, category3, category4, category5));
        }
    }
}
