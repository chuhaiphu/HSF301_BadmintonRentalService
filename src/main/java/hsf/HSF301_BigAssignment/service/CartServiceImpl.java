package hsf.HSF301_BigAssignment.service;


import hsf.HSF301_BigAssignment.pojo.Cart;
import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.repo.CartRepository;
import hsf.HSF301_BigAssignment.repo.CourtRepository;
import hsf.HSF301_BigAssignment.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;


@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CourtRepository courtRepository;

    @Override
    public void addToCart(Long userId, Long courtId) {
        Customer cus = customerRepository.findById(userId).get();
        Court court = courtRepository.findById(courtId).get();
        Cart cart = Cart.builder()
                .customer(cus)
                .court(court)
                .status(false)
                .build();
        cartRepository.save(cart);
    }

    @Override
    public void clearExpiredCarts() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(5);
        List<Cart> expiredCarts = cartRepository.findByStatusAndAddedAtBefore(false, expirationTime);
        cartRepository.deleteAll(expiredCarts);
    }

    @Override
    public void markAsPaid(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setStatus(true);
        cartRepository.save(cart);
    }
}
