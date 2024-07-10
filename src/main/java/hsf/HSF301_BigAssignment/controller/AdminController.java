package hsf.HSF301_BigAssignment.controller;

import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @GetMapping({ "", "/", "/customers" })
    public String showAdminCustomerManagement(@RequestParam(required = false) String search, Model model) {
        List<Customer> customerList;
        if (search != null && !search.isEmpty()) {
            customerList = customerService.searchCustomers(search);
        } else {
            customerList = customerService.findAll();
        }
        model.addAttribute("customerList", customerList);
        model.addAttribute("search", search);
        return "admin-customer-management";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            customerService.save(customer);
            return "redirect:/admin/customers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding customer: " + e.getMessage());
            return showAdminCustomerManagement("", model);
        }
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            customerService.update(customer);
            return "redirect:/admin/customers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating customer: " + e.getMessage());
            return showAdminCustomerManagement(null, model);
        }
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id, Model model) {
        try {
            customerService.delete(id.longValue());
            return "redirect:/admin/customers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deleting customer: " + e.getMessage());
            return showAdminCustomerManagement("", model);
        }
    }
}
