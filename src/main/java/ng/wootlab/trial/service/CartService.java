package ng.wootlab.trial.service;

import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.repository.CartRepository;
import ng.wootlab.trial.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final AuthenticationService authenticationService;

    public CartService(CartRepository cartRepository, CustomerRepository customerRepository,
                       AuthenticationService authenticationService) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.authenticationService = authenticationService;
    }

    /**
     * Get list of products in customer's cart.
     *
     * @return list of cart objects belonging to customer.
     */
    public List<Cart> getCustomersCart() {
        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        return cartRepository.findAllByCustomer(customer);
    }
}
