package hsf.HSF301_BigAssignment.controller;

import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.pojo.Payment;
import hsf.HSF301_BigAssignment.service.CourtService;
import hsf.HSF301_BigAssignment.service.CustomerService;
import hsf.HSF301_BigAssignment.service.PaymentService;
import hsf.HSF301_BigAssignment.utils.S3Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CourtService courtService;
    @Autowired
    private PaymentService paymentService;

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

    @GetMapping("/courts")
    public String showAdminCourtManagement(@RequestParam(required = false) String search, Model model) {
        List<Court> courtList;
        if (search != null && !search.isEmpty()) {
            courtList = courtService.searchCourts(search);
        } else {
            courtList = courtService.findAll();
        }
        model.addAttribute("courtList", courtList);
        model.addAttribute("search", search);
        return "admin-court-management";
    }

    @PostMapping("/addCourt")
    public String addCourt(@ModelAttribute Court court, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, Model model) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = S3Utils.uploadFile(imageFile);
                court.setImage(imageUrl);
            }
            courtService.save(court);
            return "redirect:/admin/courts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding court: " + e.getMessage());
            return showAdminCourtManagement("", model);
        }
    }


    @PostMapping("/updateCourt")
    public String updateCourt(@ModelAttribute Court court, @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        try {
            if (!imageFile.isEmpty()) {
                String imageUrl = S3Utils.uploadFile(imageFile);
                court.setImage(imageUrl);
            }
            courtService.update(court);
            return "redirect:/admin/courts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating court: " + e.getMessage());
            return showAdminCourtManagement("", model);
        }
    }


    @GetMapping("/deleteCourt/{id}")
    public String deleteCourt(@PathVariable Long id, Model model) {
        try {
            courtService.deactivateCourt(id);
            return "redirect:/admin/courts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deactivating court: " + e.getMessage());
            return showAdminCourtManagement("", model);
        }
    }
    
    @GetMapping("/payments")
    public String showAdminPaymentManagement(@RequestParam(required = false) String search, Model model) {
        List<Payment> paymentList;
        if (search != null && !search.isEmpty()) {
            paymentList = paymentService.searchPayments(search);
        } else {
            paymentList = paymentService.findAll();
        }
        model.addAttribute("customerList", customerService.findAll());

        model.addAttribute("courtList", courtService.findAll());
        model.addAttribute("paymentList", paymentList);
        model.addAttribute("search", search);
        return "admin-payment-management";
    }

    @PostMapping("/addPayment")
    public String addPayment(@ModelAttribute Payment payment, Model model) {
        try {
            paymentService.save(payment);
            return "redirect:/admin/payments";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding payment: " + e.getMessage());
            return showAdminPaymentManagement("", model);
        }
    }

    @PostMapping("/updatePayment")
    public String updatePayment(@ModelAttribute Payment payment, Model model) {
        try {
            paymentService.update(payment);
            return "redirect:/admin/payments";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating payment: " + e.getMessage());
            return showAdminPaymentManagement(null, model);
        }
    }

    @GetMapping("/deletePayment/{id}")
    public String deletePayment(@PathVariable Long id, Model model) {
        try {
            paymentService.delete(id);
            return "redirect:/admin/payments";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deleting payment: " + e.getMessage());
            return showAdminPaymentManagement("", model);
        }
    }
}
