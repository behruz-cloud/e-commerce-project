package uz.pdp.ecommerceproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.ecommerceproject.entity.Attachment;
import uz.pdp.ecommerceproject.entity.AttachmentContent;
import uz.pdp.ecommerceproject.entity.Product;
import uz.pdp.ecommerceproject.repo.AttachmentContentRepository;
import uz.pdp.ecommerceproject.repo.AttachmentRepository;
import uz.pdp.ecommerceproject.repo.CategoryRepository;
import uz.pdp.ecommerceproject.repo.ProductRepository;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductsController {


    private final ProductRepository productRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    private final CategoryRepository categoryRepository;
    //Admin uchun hamma mahsulotlarni show qilish.Va uni pagega yuborish
    @GetMapping("/showProducts")
    public String showProducts(Model model) {
        List<Product> all = productRepository.findAll();
        model.addAttribute("products", all);
        return "admin/products";
    }


    //mahsulotni o'chirish qilish uchun method.Bu faqat admin tomonida bajariladi.
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") Integer id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/product/showProducts";
    }


    //mahsulotni edit qilish uchun method.Bu faqat admin tomonida bajariladi.
    @PostMapping
    public String edit(@RequestParam("id") Integer id, Model model) {
        //chala qolgan
        return "redirect:/product/showProducts";
    }


    //mahsulot qo'shish uchun method.Bu faqat admin tomonida bajariladi.
    @PostMapping("/add")
    public String addProduct(HttpServletRequest request, Model model, @RequestParam MultipartFile file) throws IOException {
        Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String name = request.getParameter("name");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Attachment attachment = saveAttachment(file);

        Product product = new Product(
                name,price,categoryRepository.getById(categoryId),attachment
        );
        productRepository.save(product);
        return "redirect:/product/showProducts";
    }
    //mahsulot rasmini saqlash uchun method
    private Attachment saveAttachment(MultipartFile file) throws IOException {
        if (file != null) {
            Attachment attachment = new Attachment(file.getOriginalFilename());
            attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent(
                    file.getBytes(),
                    attachment
            );
            attachmentContentRepository.save(attachmentContent);
            return attachment;
        }else {
            return null;
        }
    }
}
