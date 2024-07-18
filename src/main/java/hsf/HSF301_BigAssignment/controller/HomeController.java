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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final CustomerService customerService;
    private final ICourtService courtService;

    @GetMapping()
    public String getHomePage(Model model, HttpSession session) {
        List<Court> activeCourts = courtService.findAll();
        model.addAttribute("courts", activeCourts);
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("customer", customer);
        }
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

    @PostMapping("/profile/update-info")
    public String updateProfileInfo(@ModelAttribute Customer updatedCustomer,
                                    HttpSession session,
                                    Model model) {
        Customer currentCustomer = (Customer) session.getAttribute("customer");
        if (currentCustomer != null) {
            try {
                currentCustomer.setFirstName(updatedCustomer.getFirstName());
                currentCustomer.setLastName(updatedCustomer.getLastName());
                currentCustomer.setGender(updatedCustomer.getGender());
                currentCustomer.setDob(updatedCustomer.getDob());

                customerService.update(currentCustomer);
                session.setAttribute("customer", currentCustomer);
                model.addAttribute("updateStatus", "success");
            } catch (Exception e) {
                model.addAttribute("updateStatus", "failure");
            }
        }
        return "profile-page";
    }

    @PostMapping("/profile/update-password")
    public String updatePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmNewPassword,
                                 HttpSession session,
                                 Model model) {
        Customer currentCustomer = (Customer) session.getAttribute("customer");
        if (currentCustomer != null) {
            try {
                if (!customerService.validatePassword(currentCustomer, oldPassword)) {
                    model.addAttribute("updateStatus", "incorrectPassword");
                } else if (!newPassword.equals(confirmNewPassword)) {
                    model.addAttribute("updateStatus", "passwordMismatch");
                } else {
                    customerService.updatePassword(currentCustomer, newPassword);
                    model.addAttribute("updateStatus", "success");
                }
            } catch (Exception e) {
                model.addAttribute("updateStatus", "failure");
            }
            // Add this line to ensure the customer object is always in the model
            model.addAttribute("customer", currentCustomer);
        }
        return "profile-page";
    }

}
