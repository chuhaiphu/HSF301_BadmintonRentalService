//package hsf.HSF301_BigAssignment.controller;
//
//
//import java.util.List;
//
//import hsf.HSF301_BigAssignment.pojo.Customer;
//import hsf.HSF301_BigAssignment.service.ICourtService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("/api/v1/cart")
//public class CartController {
//     @Autowired
//    private ICartService cartService;
//
//     @Autowired
//     private ICourtService courtService;
//
//    @GetMapping("/add-cart/{courtId}")
//    public String addForm(RedirectAttributes redirect, @PathVariable("courtId") Long courtId, HttpSession session){
//        Customer customer = (Customer) session.getAttribute("customer");
//
//        if(cartService.checkCartPaid(customer.getId())){
//            cartService.addToCart(customer.getId(), courtId);
//            redirect.addFlashAttribute("message", "Add cart success !!!");
//        } else {
//            redirect.addFlashAttribute("warning", "Add cart unsuccessfully please pay your court first !!!");
//        }
//        return "redirect:/home";
//    }
//
//    @GetMapping("/view-cart")
//    public String viewCart(Model model, HttpSession session){
//        Customer customer = (Customer) session.getAttribute("customer");
//        List<Cart> carts = cartService.viewCart(customer.getId());
//        model.addAttribute("carts", carts);
//        return "cartItem";
//    }
//
//
//    @GetMapping("/delete-cart")
//    public String deleteCart(RedirectAttributes redirect, @RequestParam("cartId") Long cartId){
//        if(cartId != null) {
//            cartService.deleteCart(cartId);
//            redirect.addFlashAttribute("message", "Delete successfully !!!");
//        } else {
//            redirect.addFlashAttribute("warning", "There are something wrong !");
//        }
//        return "redirect:/api/v1/cart/view-cart";
//    }
//}
