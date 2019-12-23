package ng.wootlab.trial.auth;

import ng.wootlab.trial.model.Customer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class AuthenticatedCustomer extends User {
    private Customer customer;

    public AuthenticatedCustomer(Customer customer) {
        super(customer.getEmail(), customer.getPassword(), AuthorityUtils.createAuthorityList(
                customer.getRole().getRole()));
        this.customer = customer;
    }

    public Integer getId() {
        return this.customer.getId();
    }

    public String getFirstName() {
        return this.customer.getFirstName();
    }

    public String getSurname() {
        return this.customer.getSurname();
    }

    public String getEmail() {
        return this.customer.getEmail();
    }
}
