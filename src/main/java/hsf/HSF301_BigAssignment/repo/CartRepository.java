package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Cart;
import hsf.HSF301_BigAssignment.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN false ELSE true END FROM Cart c WHERE c.customer.id = :userId AND c.status = false")
    Boolean checkCartByUserId(Long userId);

    @Query("SELECT c FROM Cart c WHERE c.customer.id = :id AND c.status = false")
    List<Cart> findAllFalseByCustomer_id(Long id);
}
