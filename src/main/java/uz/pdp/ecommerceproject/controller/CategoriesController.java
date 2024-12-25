package uz.pdp.ecommerceproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.ecommerceproject.entity.Category;
import uz.pdp.ecommerceproject.repo.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoriesController {


    private final CategoryRepository categoryRepository;

    public CategoriesController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/showCategories")
    public String showCategories(Model model) {
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categories", all);
        return "admin/categories";
    }

    @PostMapping("/delete")
    public String editCategory(@RequestParam Integer id, Model model) {
        Category category = categoryRepository.getById(id);
        categoryRepository.delete(category);
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/categories";
    }

    @PostMapping("/add")
    public String addCategory(@RequestParam String name, Model model) {
        Category category = new Category(name);
        categoryRepository.save(category);
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/categories";
    }
}
