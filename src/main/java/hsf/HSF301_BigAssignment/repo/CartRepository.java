package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Cart;
import hsf.HSF301_BigAssignment.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserAndAddedAtAfter(Customer customer, LocalDateTime addedAt);
    List<Cart> findByStatusAndAddedAtBefore(Boolean status, LocalDateTime addedAt);
}
