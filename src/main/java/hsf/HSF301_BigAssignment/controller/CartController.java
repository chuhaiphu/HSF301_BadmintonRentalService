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

@Controller
public class CartController {
     @Autowired
    private CartService cartService;

     @Autowired
     private CourtService courtService;

     @GetMapping("/add-cart/{courtId}")
    public String addForm(Model model, @PathVariable("courtId") Integer courtId, HttpSession session){
         Object cus = session.getAttribute("customer");
         Customer take = (Customer) cus;

         if(!cartService.checkCartPaid(take.getId())){
             cartService.addToCart(take.getId(), courtId);
             model.addAttribute("message", "Add cart success !!!");
         } else {
             model.addAttribute("warning", "Add cart unsuccessfully please pay your court first !!!");
         }
         return "addCartForm";
     }

     @GetMapping("/view-cart")
     public String viewCart(Model model, HttpSession session){
         Object cus = session.getAttribute("customer");
         Customer take = (Customer) cus;
         List<Cart> carts = cartService.viewCart(take.getId());
         model.addAttribute("carts", carts);
         return "cartItem";
     }
}
