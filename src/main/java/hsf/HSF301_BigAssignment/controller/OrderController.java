package hsf.HSF301_BigAssignment.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hsf.HSF301_BigAssignment.pojo.*;
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
@RequestMapping("/order")
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
        try {
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
            for(Slot slot : slots){
                slot.setStatus(false);
                slotService.saveSlot(slot);
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("warning", "There are something wrong !");
        }
        return "redirect:/home";
    }

    @GetMapping("/view-order")
    public String viewOrder(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orders = orderService.getByUserId(customer.getId());
        List<OrderDTO> orderDTOS = orders.stream().map(order -> {
            List<Slot> slots = order.getOrderDetails()
                    .stream()
                    .map(orderDetail -> slotService.getSlotById(orderDetail.getSlotId()))
                    .toList();
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setCustomer(order.getCustomer());
            orderDTO.setOrderDetails(order.getOrderDetails());
            orderDTO.setCourt(order.getCourt());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setBookDate(order.getBookDate());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orderDTO.setSlots(slots);
            return orderDTO;
        }).toList();
        model.addAttribute("orders", orderDTOS);
        return "order-items";
    }


    @GetMapping("/delete-order")
    public String deleteOrder(RedirectAttributes redirect, @RequestParam("orderId") Long orderId){
        if(orderId != null) {
            Order order = orderService.getOrderById(orderId);
            order.setStatus(false);
            orderService.saveOrder(order);
            for (OrderDetail orderDetail: order.getOrderDetails()) {
                Slot slot = slotService.getSlotById(orderDetail.getSlotId());
                slot.setStatus(true);
                slotService.saveSlot(slot);
            }
            redirect.addFlashAttribute("message", "Delete successfully !!!");
        } else {
            redirect.addFlashAttribute("warning", "There are something wrong !");
        }
        return "redirect:/api/v1/order/view-order";
    }
}
