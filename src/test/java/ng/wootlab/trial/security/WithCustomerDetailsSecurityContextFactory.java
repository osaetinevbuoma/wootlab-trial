package ng.wootlab.trial.security;

import ng.wootlab.trial.auth.AuthenticatedCustomer;
import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.model.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithCustomerDetailsSecurityContextFactory implements
        WithSecurityContextFactory<WithMockCustomer> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomer annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Customer customer = new Customer(annotation.firstName(), annotation.surname(),
                annotation.email(), new BCryptPasswordEncoder().encode(annotation.password()));
        customer.setId(annotation.id());
        customer.setRole(new Role(annotation.role()));
        AuthenticatedCustomer authenticatedCustomer = new AuthenticatedCustomer(customer);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticatedCustomer, authenticatedCustomer.getPassword(),
                authenticatedCustomer.getAuthorities());
        context.setAuthentication(authentication);

        return context;
    }
}
