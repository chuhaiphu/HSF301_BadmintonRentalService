package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
//    @Query("SELECT p FROM Payment p WHERE " +
//            "CAST(p.id AS string) LIKE CONCAT('%', :searchTerm, '%') OR " +
//            "CAST(p.finalPrice AS string) LIKE CONCAT('%', :searchTerm, '%') OR " +
//            "CAST(p.payAt AS string) LIKE CONCAT('%', :searchTerm, '%') OR " +
//            "LOWER(p.customer.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
//            "LOWER(p.customer.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
//            "LOWER(p.court.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
//    List<Payment> searchPayments(@Param("searchTerm") String searchTerm);
}
