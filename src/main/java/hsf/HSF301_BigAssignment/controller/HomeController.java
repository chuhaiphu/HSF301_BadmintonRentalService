package hsf.HSF301_BigAssignment.controller;

import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.service.CourtService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final CourtService courtService;
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
    public String search(@RequestParam("query") String query, Model model) {
        List<Court> courts = courtService.searchCourts(query);
        model.addAttribute("courtsSearch", courts);
        model.addAttribute("courts", courtService.getAllCourts());
        return "home-page";
    }

}
