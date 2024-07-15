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
public class CartServiceImpl implements ICartService {

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
                .addedAt(LocalDateTime.now())
                .status(false)
                .build();
        cartRepository.save(cart);
    }


    @Override
    public Boolean checkCartPaid(Long customerId) {
      return cartRepository.checkCartByUserId(customerId);
    }

    @Override
    public List<Cart> viewCart(Long customerId) {
        List<Cart> carts = cartRepository.findAllFalseByCustomer_id(customerId);
        return carts;
    }

    @Override
    public Cart findById(Long cartId) {
        return cartRepository.findById(cartId).get();
    }
}
