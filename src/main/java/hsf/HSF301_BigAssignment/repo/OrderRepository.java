package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomer_Id(Long id);
    List<Order> findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCaseOrCourt_NameContainingIgnoreCase(String firstName, String lastName, String courtName);

    List<Order> findAllByCourt_Id(Long courtId);
}
