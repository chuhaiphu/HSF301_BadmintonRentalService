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
    public void addToCart(Integer userId, Integer courtId) {
        Customer cus = customerRepository.findById((long)userId).get();
        Court court = courtRepository.findById((long)courtId).get();
        Cart cart = Cart.builder()
                .customer(cus)
                .court(court)
                .status(false)
                .build();
        cartRepository.save(cart);
    }



    @Override
    public void markAsPaid(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setStatus(true);
        cartRepository.save(cart);
    }

    @Override
    public Boolean checkCartPaid(Integer customerId) {
      return cartRepository.checkCartByUserId(customerId);
    }

    @Override
    public List<Cart> viewCart(Integer customerId) {
        List<Cart> carts = cartRepository.findAllFalseByCustomer_id(customerId);
        return carts;
    }
}
