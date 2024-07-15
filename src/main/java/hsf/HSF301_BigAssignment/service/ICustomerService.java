package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    void update(Customer customer);

    List<Customer> findAll();

    List<Customer> searchCustomers(String searchTerm);

    void save(Customer customer);

    void delete(Long id);

    Optional<Customer> findById(Long id);
}
