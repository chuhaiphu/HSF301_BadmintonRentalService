package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Payment;

import java.util.List;

public interface IPaymentService {

    List<Payment> findAll();

    List<Payment> searchPayments(String searchTerm);

    void save(Payment payment);

    Payment update(Payment payment);

    void delete(Long id);
}
