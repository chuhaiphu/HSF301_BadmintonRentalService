package hsf.HSF301_BigAssignment.controller;

import hsf.HSF301_BigAssignment.pojo.Cart;
import hsf.HSF301_BigAssignment.pojo.Payment;
import hsf.HSF301_BigAssignment.service.CartService;
import hsf.HSF301_BigAssignment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class PaymentController {

    @Autowired
    private CartService cartService;

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/payment-form/{cartId}")
    public String paymentCart(Model model, @PathVariable("cartId") Long cartId){
        Cart cart = cartService.findById(cartId);

        Payment payment = new Payment();
        payment.setCart(cart);
        payment.setFinalPrice(cart.getCourt().getPrice());
        payment.setPayAt(LocalDateTime.now());

        model.addAttribute("payment", payment);
        return "payment-form";
    }


    @PostMapping("/process-payment")
    public String paymentProcess(RedirectAttributes redirect, @ModelAttribute("payment") Payment payment){
        paymentService.save(payment);
        redirect.addFlashAttribute("message", "payment is successfully !!!");

        return "redirect:/view-cart";
    }
}
