package uz.pdp.ecommerceproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerceapp.basket.Basket;
import uz.pdp.ecommerceapp.entity.Product;
import uz.pdp.ecommerceapp.repo.ProductRepository;

import java.util.Objects;


@Controller
@RequestMapping("/basketPage")
@RequiredArgsConstructor
public class BasketController {
    private final ProductRepository productRepository;

    @PostMapping("/add")
    public String addBasket(@RequestParam(required = false) Integer id,
                            Model model,
                            @RequestParam(required = false) Integer selectedCategoryId,
                            HttpServletRequest request) {
        Product product = productRepository.getById(id);
        Basket basket = (Basket) Objects.requireNonNullElse(request.getSession().getAttribute("basket"), new Basket());

        if (!isProductInBasket(basket, product)) {
            basket.getBasket().put(product, null);
            request.getSession().setAttribute("basket", basket);
        }

        model.addAttribute("selectedCategoryId", selectedCategoryId);
        model.addAttribute("basket", basket);
        return "redirect:/homePage?selectedCategoryId=" + selectedCategoryId;
    }

    @PostMapping("/remove")
    public String removeBasket(@RequestParam(required = false) Integer id,
                               @RequestParam(required = false) Integer selectedCategoryId,
                               Model model,
                               HttpServletRequest request) {
        Product product = productRepository.getById(id);
        Basket basket = (Basket) Objects.requireNonNullElse(request.getSession().getAttribute("basket"), new Basket());

        if (isProductInBasket(basket, product)) {
            basket.getBasket().remove(product);
            request.getSession().setAttribute("basket", basket);
        }

        model.addAttribute("selectedCategoryId", selectedCategoryId);
        model.addAttribute("basket", basket);
        return "redirect:/homePage?selectedCategoryId=" + selectedCategoryId;
    }


    @GetMapping("/show")
    public String show(@SessionAttribute Basket basket, Model model) {
        if (!basket.getBasket().isEmpty()) {
            model.addAttribute("basket", basket);
            return "/home/basket";
        } else {
            return "redirect:/homePage";
        }
    }
    @GetMapping("/increment")
    public String incrementQuantity(@RequestParam Integer id,@SessionAttribute Basket basket,HttpServletRequest request) {
        Product product = productRepository.getById(id);
        basket.getBasket().compute(product, (k, currentQuantity) -> currentQuantity == null ? 1 : currentQuantity + 1);
        request.getSession().setAttribute("basket", basket);
        return "redirect:/basketPage/show"; // Savat sahifasiga qaytadi
    }

    @GetMapping("/decrement")
    public String decrementQuantity(@RequestParam Integer id,@SessionAttribute Basket basket,HttpServletRequest request) {
        Product product = productRepository.getById(id);
        Integer currentQuantity = basket.getBasket().get(product);
        if (currentQuantity != null && currentQuantity > 1) {
            basket.getBasket().put(product, currentQuantity - 1);
        } else {
            basket.getBasket().remove(product); // Agar miqdor 1 yoki undan kam bo'lsa, savatdan o'chiradi
        }
        request.getSession().setAttribute("basket", basket);
        return "redirect:/basketPage/show"; // Savat sahifasiga qaytadi
    }

    public boolean isProductInBasket(Basket basket, Product product) {
        return basket.getBasket().containsKey(product);
    }

}
