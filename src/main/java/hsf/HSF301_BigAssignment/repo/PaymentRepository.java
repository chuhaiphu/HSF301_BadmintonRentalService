package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
