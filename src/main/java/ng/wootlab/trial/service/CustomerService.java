package ng.wootlab.trial.service;

import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.model.Role;
import ng.wootlab.trial.repository.CustomerRepository;
import ng.wootlab.trial.repository.RoleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("authenticated")
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    public CustomerService(CustomerRepository customerRepository, RoleRepository roleRepository) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Registers a new customer by first ensuring that an account with the provided email address
     * does not already exist.
     *
     * @param customer a new customer object
     * @return the created customer object or null if a customer already has the inputted email
     *  address
     */
    public Customer registerCustomer(Customer customer) {
        if (null != customerRepository.findByEmail(customer.getEmail())) {
            return null;
        }

        Role role = roleRepository.findByRole("ROLE_USER");
        String password = new BCryptPasswordEncoder().encode(customer.getPassword());
        Customer newCustomer = new Customer(customer.getFirstName(), customer.getSurname(),
                customer.getEmail(), password);
        newCustomer.setRole(role);
        customerRepository.save(newCustomer);

        return customer;
    }
}
