package ng.wootlab.trial.auth;

import ng.wootlab.trial.model.Customer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class AuthenticatedCustomer extends User {
    public AuthenticatedCustomer(Customer customer) {
        super(customer.getEmail(), customer.getPassword(), AuthorityUtils.createAuthorityList(
                customer.getRole().getRole()));
    }
}
