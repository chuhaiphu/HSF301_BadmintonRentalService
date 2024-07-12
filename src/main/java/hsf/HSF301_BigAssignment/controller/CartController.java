package hsf.HSF301_BigAssignment.controller;


import java.util.List;

import hsf.HSF301_BigAssignment.pojo.Cart;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.service.CartService;
import hsf.HSF301_BigAssignment.service.CourtService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {
     @Autowired
    private CartService cartService;

     @Autowired
     private CourtService courtService;

     @GetMapping("/add-cart/{courtId}")
    public String addForm(RedirectAttributes redirect, @PathVariable("courtId") Long courtId, HttpSession session){
         Customer customer = (Customer) session.getAttribute("customer");

         if(cartService.checkCartPaid(customer.getId())){
             cartService.addToCart(customer.getId(), courtId);
             redirect.addFlashAttribute("message", "Add cart success !!!");
         } else {
             redirect.addFlashAttribute("warning", "Add cart unsuccessfully please pay your court first !!!");
         }
         return "redirect:/home";
     }

     @GetMapping("/view-cart")
     public String viewCart(Model model, HttpSession session){
         Customer customer = (Customer) session.getAttribute("customer");
         List<Cart> carts = cartService.viewCart(customer.getId());
         model.addAttribute("carts", carts);
         return "cartItem";
     }
}
