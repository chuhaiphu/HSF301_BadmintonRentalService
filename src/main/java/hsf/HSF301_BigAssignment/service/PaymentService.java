package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Payment;
import hsf.HSF301_BigAssignment.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> searchPayments(String searchTerm) {
        return paymentRepository.searchPayments(searchTerm);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
