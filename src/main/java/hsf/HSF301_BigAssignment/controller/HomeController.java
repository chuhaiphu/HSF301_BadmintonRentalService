package hsf.HSF301_BigAssignment.controller;

import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.service.CourtService;
import hsf.HSF301_BigAssignment.service.ICourtService;
import hsf.HSF301_BigAssignment.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final CustomerService customerService;
    private final ICourtService courtService;
    @GetMapping()
    public String getHomePage(Model model, HttpSession session) {
        List<Court> courts = courtService.getAllCourts();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("customer", customer);
        }
        model.addAttribute("courts", courts);
        return "home-page";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, HttpSession session) {
        List<Court> courts = courtService.searchCourts(query);
        model.addAttribute("courtsSearch", courts);
        model.addAttribute("courts", courtService.getAllCourts());
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("customer", customer);
        }
        return "home-page";
    }


    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "profile-page";
        }
        return "redirect:/login";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Customer updatedCustomer,
                                @RequestParam String oldPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmNewPassword,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        Customer currentCustomer = (Customer) session.getAttribute("customer");
        if (currentCustomer != null) {
            if (!oldPassword.equals(currentCustomer.getPassword())) {
                redirectAttributes.addFlashAttribute("error", "Incorrect old password");
                return "redirect:/home/profile";
            }

            if (!newPassword.isEmpty() && !newPassword.equals(confirmNewPassword)) {
                redirectAttributes.addFlashAttribute("error", "New passwords do not match");
                return "redirect:/home/profile";
            }

            // Update customer information
            currentCustomer.setUsername(updatedCustomer.getUsername());
            currentCustomer.setFirstName(updatedCustomer.getFirstName());
            currentCustomer.setLastName(updatedCustomer.getLastName());
            currentCustomer.setGender(updatedCustomer.getGender());
            currentCustomer.setDob(updatedCustomer.getDob());

            // Update password if a new one is provided
            if (!newPassword.isEmpty()) {
                currentCustomer.setPassword(newPassword);
            }

            customerService.update(currentCustomer);
            session.setAttribute("customer", currentCustomer);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        }
        return "redirect:/home/profile";
    }
}
