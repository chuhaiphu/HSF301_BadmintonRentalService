package hsf.HSF301_BigAssignment.controller;

import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.pojo.Order;
import hsf.HSF301_BigAssignment.pojo.Slot;
import hsf.HSF301_BigAssignment.repo.SlotRepository;
import hsf.HSF301_BigAssignment.service.*;
import hsf.HSF301_BigAssignment.utils.S3Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICourtService courtService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private SlotRepository slotRepository;

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
    public String addCourt(@ModelAttribute Court court,
                           @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, Model model) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = S3Utils.uploadFile(imageFile);
                court.setImage(imageUrl);
            }

            // Create new slots for the court
            List<Slot> newSlots = Arrays.asList(
                    new Slot(null, LocalTime.of(8, 0), LocalTime.of(12, 0), true, court),
                    new Slot(null, LocalTime.of(12, 0), LocalTime.of(16, 0), true, court),
                    new Slot(null, LocalTime.of(16, 0), LocalTime.of(20, 0), true, court),
                    new Slot(null, LocalTime.of(20, 0), LocalTime.of(23, 59), true, court)
            );

            court.setSlots(newSlots);

            courtService.save(court);

            return "redirect:/admin/courts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding court: " + e.getMessage());
            return showAdminCourtManagement("", model);
        }
    }


    @PostMapping("/updateCourt")
    public String updateCourt(@ModelAttribute Court court, @RequestParam("imageFile") MultipartFile imageFile,
            Model model) {
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
            courtService.delete(id);
            return "redirect:/admin/courts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deleting court: " + e.getMessage());
            return showAdminCourtManagement("", model);
        }
    }

    @GetMapping("/orders")
    public String showAdminOrderManagement(@RequestParam(required = false) String search, Model model) {
        List<Order> orderList;
        if (search != null && !search.isEmpty()) {
            orderList = orderService.searchOrders(search);
        } else {
            orderList = orderService.getAllOrders();
        }
        model.addAttribute("orderList", orderList);
        model.addAttribute("search", search);
        return "admin-order-management";
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public Order getOrderDetails(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id, Model model) {
        try {
            orderService.disableOrder(id);
            return "redirect:/admin/orders";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error disabling order: " + e.getMessage());
            return showAdminOrderManagement("", model);
        }
    }
}
