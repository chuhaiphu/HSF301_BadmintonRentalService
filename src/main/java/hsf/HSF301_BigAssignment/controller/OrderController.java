package hsf.HSF301_BigAssignment.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hsf.HSF301_BigAssignment.pojo.*;
import hsf.HSF301_BigAssignment.repo.OrderRepository;
import hsf.HSF301_BigAssignment.service.ICourtService;
import hsf.HSF301_BigAssignment.service.IOrderDetailService;
import hsf.HSF301_BigAssignment.service.IOrderService;
import hsf.HSF301_BigAssignment.service.ISlotService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/v1/cart")
public class OrderController {
     @Autowired
    private IOrderService orderService;

     @Autowired
     private ICourtService courtService;

     @Autowired
     private ISlotService slotService;

     @Autowired
     private IOrderDetailService orderDetailService;


    @GetMapping("/add-order/{courtId}")
    public String addForm(RedirectAttributes redirect, @PathVariable("courtId") Long courtId, @ModelAttribute("listSlot") List<Long> listSlotId, @PathVariable("bookedDate")LocalDate bookedDate, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        Court court = courtService.findById(courtId);
        List<Slot> slots = new ArrayList<>();
        for(Long slotId : listSlotId){
            Slot slot = slotService.getSlotById(slotId);
            slots.add(slot);
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Slot slot : slots){
            OrderDetail orderDetail = OrderDetail.builder()
                    .price(court.getPrice())
                    .slotId(slot.getId())
                    .build();
            orderDetails.add(orderDetailService.saveOrderDetail(orderDetail));
        }
        Order order = new Order();
        order.setCustomer(customer);
        order.setCourt(court);
        order.setOrderDetails(orderDetails);
        order.setTotalPrice(court.getPrice() * orderDetails.size());
        order.setStatus(true);
        order.setBookDate(bookedDate);
        orderService.saveOrder(order);


        return "redirect:/home";
    }

    @GetMapping("/view-cart")
    public String viewCart(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orders = orderService.getByUserId(customer.getId());
        model.addAttribute("orders", orders);
        return "cartItem";
    }


    @GetMapping("/delete-cart")
    public String deleteCart(RedirectAttributes redirect, @RequestParam("orderId") Long orderId){
        if(orderId != null) {
            orderService.deleteOrder(orderId);
            redirect.addFlashAttribute("message", "Delete successfully !!!");
        } else {
            redirect.addFlashAttribute("warning", "There are something wrong !");
        }
        return "redirect:/api/v1/cart/view-cart";
    }
}
