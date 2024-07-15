package hsf.HSF301_BigAssignment.controller;


import java.util.List;

import hsf.HSF301_BigAssignment.pojo.Cart;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.service.ICartService;
import hsf.HSF301_BigAssignment.service.ICourtService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/v1/cart")
public class CartController {
     @Autowired
    private ICartService ICartService;

     @Autowired
     private ICourtService courtService;

     @GetMapping("/add-cart/{courtId}")
    public String addForm(RedirectAttributes redirect, @PathVariable("courtId") Long courtId, HttpSession session){
         Customer customer = (Customer) session.getAttribute("customer");

         if(ICartService.checkCartPaid(customer.getId())){
             ICartService.addToCart(customer.getId(), courtId);
             redirect.addFlashAttribute("message", "Add cart success !!!");
         } else {
             redirect.addFlashAttribute("warning", "Add cart unsuccessfully please pay your court first !!!");
         }
         return "redirect:/home";
     }

     @GetMapping("/view-cart")
     public String viewCart(Model model, HttpSession session){
         Customer customer = (Customer) session.getAttribute("customer");
         List<Cart> carts = ICartService.viewCart(customer.getId());
         model.addAttribute("carts", carts);
         return "cartItem";
     }
}
