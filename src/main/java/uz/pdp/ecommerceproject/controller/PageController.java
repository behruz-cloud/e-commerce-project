package uz.pdp.ecommerceproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import uz.pdp.ecommerceproject.entity.Category;
import uz.pdp.ecommerceproject.entity.Product;
import uz.pdp.ecommerceproject.repo.CategoryRepository;
import uz.pdp.ecommerceproject.repo.ProductRepository;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/homePage")
    public String home(Model model, @RequestParam(required = false) Integer selectedCategoryId) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        if (selectedCategoryId != null) {
            List<Product> products = productRepository.findAll().stream().filter(product -> product.getCategory().getId().equals(selectedCategoryId)).toList();
            model.addAttribute("selectedCategoryId", selectedCategoryId);
            model.addAttribute("products", products);
            return "home/home";
        } else if (model.getAttribute("selectedCategoryId") != null) {
            Integer categoryId = (Integer) model.getAttribute("selectedCategoryId");
            model.addAttribute("selectedCategoryId", categoryId);
            List<Product> products = productRepository.findAll().stream()
                    .filter(product -> product.getCategory().getId().equals(categoryId))
                    .sorted(Comparator.comparing(Product::getName)) // ID bo‘yicha sorting
                    .toList();
            model.addAttribute("products", products);
            return "home/home";
        } else {
            return "home/home";
        }
    }

    @GetMapping("/addCategory")
    public String addCategory() {
        return "admin/addCategory";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categories", all);
        return "admin/addProduct";
    }

//    @PostMapping("/show")
//    public String basket(Model model, @SessionAttribute Basket basket) {
//        model.addAttribute("basket", basket);
//        return "home/basket";
//    }

}
