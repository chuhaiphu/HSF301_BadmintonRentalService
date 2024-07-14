
package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Cart;
import java.util.List;

public interface CartService {
    void addToCart(Long userId, Long courtId);

    Boolean checkCartPaid(Long customerId);

    List<Cart> viewCart(Long customerId);

    Cart findById(Long cartId);

    void deleteCart(Long cartId);
}

