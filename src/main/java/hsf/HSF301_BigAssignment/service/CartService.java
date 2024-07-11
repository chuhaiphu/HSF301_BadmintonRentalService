package hsf.HSF301_BigAssignment.service;

public interface CartService {
    void addToCart(Long userId, Long productId);

    void clearExpiredCarts();

    void markAsPaid(Long cartId);
}
