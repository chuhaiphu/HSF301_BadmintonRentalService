package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.repo.CustomerRepository;

import org.springframework.stereotype.Service;
import hsf.HSF301_BigAssignment.pojo.Customer;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void update(Customer customer) {
        customerRepo.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public List<Customer> searchCustomers(String searchTerm) {
        return customerRepo.searchCustomers(searchTerm);
    }

    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    public void delete(Long id) {
        customerRepo.deleteById(id);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    public boolean validatePassword(Customer customer, String oldPassword) {
        return customer.getPassword().equals(oldPassword);
    }

    public void updatePassword(Customer customer, String newPassword) {
        customer.setPassword(newPassword);
        customerRepo.save(customer);
    }

    public void updateProfile(Customer customer) {
        customerRepo.save(customer);
    }

}
