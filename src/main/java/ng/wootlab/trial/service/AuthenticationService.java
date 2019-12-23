package ng.wootlab.trial.service;

import ng.wootlab.trial.auth.AuthenticatedCustomer;
import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public AuthenticationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);
        return new AuthenticatedCustomer(customer);
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public AuthenticatedCustomer getAuthenticatedCustomer() {
        return (AuthenticatedCustomer) getAuthentication().getPrincipal();
    }
}
