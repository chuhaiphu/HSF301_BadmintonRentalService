package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Admin;
import hsf.HSF301_BigAssignment.pojo.Customer;
import hsf.HSF301_BigAssignment.repo.AdminRepository;
import hsf.HSF301_BigAssignment.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    @Override
    public Object login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        Admin admin = adminRepository.findByUsername(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        } else if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;

    }
}
