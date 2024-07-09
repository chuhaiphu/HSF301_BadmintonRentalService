package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
