package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Cart;
import java.util.List;

public interface CartService {
    void addToCart(Integer userId, Integer courtId);

    void markAsPaid(Integer cartId);

    Boolean checkCartPaid(Integer customerId);

    List<Cart> viewCart(Integer customerId);
}
