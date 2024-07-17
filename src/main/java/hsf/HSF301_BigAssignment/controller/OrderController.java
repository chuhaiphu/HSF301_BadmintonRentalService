package hsf.HSF301_BigAssignment.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import hsf.HSF301_BigAssignment.pojo.*;
import hsf.HSF301_BigAssignment.service.ICourtService;
import hsf.HSF301_BigAssignment.service.IOrderDetailService;
import hsf.HSF301_BigAssignment.service.IOrderService;
import hsf.HSF301_BigAssignment.service.ISlotService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/showOrderPage/{courtId}")
    public String createOrder(@PathVariable(name = "courtId") Long courtId, Model model, HttpSession session) {
        // Fetch court and available slots
        Court court = courtService.findById(courtId);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer((Customer) session.getAttribute("customer"));
        orderDTO.setCourt(court);
        orderDTO.setSlots(court.getSlots().stream().filter(Slot::isStatus).toList());
        model.addAttribute("court", court);
        model.addAttribute("order", orderDTO);
        return "order-confirmation";
    }

    @PostMapping("/add-order")
    public String addForm(@ModelAttribute("order") OrderDTO orderDTO,
                          RedirectAttributes redirect,
                          HttpSession session) {
        try {
            // Check if the selected slots are continuous
            if (!orderService.areSlotsContinuous(orderDTO.getSlots().stream().map(Slot::getId).toList())) {
                redirect.addFlashAttribute("warning", "Slots must be continuous");
                return "redirect:/order/showOrderPage/" + orderDTO.getCourt().getId();
            }
            if (orderDTO.getBookDate().isBefore(LocalDate.now())) {
                redirect.addFlashAttribute("warning", "Invalid book date");
                return "redirect:/order/showOrderPage/" + orderDTO.getCourt().getId();
            }
            List<Order> orders = orderService.getByCourtId(orderDTO.getCourt().getId());
            if (!orders.isEmpty()) {
                LocalDate bookDate = orderDTO.getBookDate();
                boolean slotConflict = orders.stream()
                        .filter(order -> order.getBookDate().equals(bookDate)) // Check for matching bookDate
                        .flatMap(order -> order.getOrderDetails().stream()) // Get OrderDetails for the order
                        .anyMatch(orderDetail -> orderDTO.getSlots().stream()
                                .anyMatch(slot -> slot.getId().equals(orderDetail.getSlotId()))); // Check for slotId match

                if (slotConflict) {
                    redirect.addFlashAttribute("warning", "Slots are not available");
                    return "redirect:/order/showOrderPage/" + orderDTO.getCourt().getId();
                }
            }

            // Get customer from session
            Customer customer = (Customer) session.getAttribute("customer");
            Court court = courtService.findById(orderDTO.getCourt().getId());

            // Create the order first
            Order order = new Order();
            order.setCustomer(customer);
            order.setCourt(court);
            order.setTotalPrice(court.getPrice() * orderDTO.getSlots().size());
            order.setStatus(true);
            order.setBookDate(orderDTO.getBookDate());
            orderService.saveOrder(order); // Save the order to get an ID

            for (Slot slot : orderDTO.getSlots()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setPrice(court.getPrice());
                orderDetail.setSlotId(slot.getId());
                orderDetail.setOrder(order);
                orderDetailService.saveOrderDetail(orderDetail);
            }
//
//            // Update slot statuses
//            for (Slot slot : orderDTO.getSlots()) {
//                slot.setStatus(false);
//                slotService.saveSlot(slot);
//            }
        } catch (Exception e) {
            redirect.addFlashAttribute("warning", e.getMessage());
        }
        return "redirect:/home";
    }

    @GetMapping("/view-order")
    public String viewOrder(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orders = orderService.getByUserId(customer.getId())
                .stream().filter(Order::getStatus).toList();
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


    @GetMapping("/cancel/{orderId}")
    public String deleteOrder(RedirectAttributes redirect, @PathVariable("orderId") Long orderId, HttpSession session) {
        if(orderId != null) {
            Customer customer = (Customer) session.getAttribute("customer");
            Order order = orderService.getOrderById(orderId);
            if (customer != null) {
                if (Objects.equals(order.getCustomer().getId(), customer.getId())) {
                    redirect.addFlashAttribute("warning", "Not Permitted!");
                }
            }
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
        return "redirect:/order/view-order";
    }
}
