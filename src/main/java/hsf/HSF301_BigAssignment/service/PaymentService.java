package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Cart;
import hsf.HSF301_BigAssignment.pojo.Payment;
import hsf.HSF301_BigAssignment.repo.CartRepository;
import hsf.HSF301_BigAssignment.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

//    public List<Payment> searchPayments(String searchTerm) {
//        return paymentRepository.searchPayments(searchTerm);
//    }

    @Transactional
    public void save(Payment payment) {
        Cart cart = payment.getCart();
        if (cart.getId() != null) {
            cart = cartRepository.findById(cart.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
        }
        cart.setStatus(true);
        cart = cartRepository.save(cart);
        payment.setCart(cart);
        paymentRepository.save(payment);
    }

    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

}
