package uz.pdp.ecommerceproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.ecommerceproject.entity.User;
import uz.pdp.ecommerceproject.repo.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestParam String phone, @RequestParam String password, Model model) {
        Optional<User> optionalUser = userRepository.getByPhoneAndPassword(phone, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("currentUser", user);
            request.getSession().setAttribute("currentUser", user);
            return "redirect:/homePage";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("currentUser");
        request.getSession().invalidate();
        return "login";
    }
}
