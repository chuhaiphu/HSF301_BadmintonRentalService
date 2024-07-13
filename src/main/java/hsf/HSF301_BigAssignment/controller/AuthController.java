package hsf.HSF301_BigAssignment.controller;

import hsf.HSF301_BigAssignment.pojo.Admin;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.service.CustomerService;
import hsf.HSF301_BigAssignment.service.IAuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;
    private final CustomerService customerService;

    @GetMapping("/")
    public String authRedirect(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null && admin == null) {
            return "redirect:/login";
        }
        else if (admin != null) {
            return "redirect:/admin";
        } else {
            return "redirect:/customer";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register-page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Customer customer, HttpSession session) {
        try {
            customer.setStatus(true);
            customerService.save(customer);
            session.setAttribute("registerStatus", "success");
            return "redirect:/login";
        } catch (Exception e) {
            session.setAttribute("registerStatus", "failure");
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Model model, HttpSession session) {
        String registerStatus = (String) session.getAttribute("registerStatus");
        if (registerStatus != null) {
            model.addAttribute("registerStatus", registerStatus);
            session.removeAttribute("registerStatus");
        }
        return "login-page";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        Object result = authService.login(email, password);
        if (result instanceof Customer customer) {
            session.setAttribute("customer", customer);
            return "redirect:/home";
        } else if (result instanceof Admin admin){
            session.setAttribute("admin", admin);
            return "redirect:/admin";
        }
        redirectAttributes.addFlashAttribute("loginStatus", "failure");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
