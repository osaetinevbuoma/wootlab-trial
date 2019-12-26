package ng.wootlab.trial.service;

import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.model.Orders;
import ng.wootlab.trial.model.Shipping;
import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.repository.CartRepository;
import ng.wootlab.trial.repository.OrderRepository;
import ng.wootlab.trial.repository.ShippingRepository;
import ng.wootlab.trial.repository.CustomerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PreAuthorize("authenticated")
public class CheckoutService {
    private final AuthenticationService authenticationService;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ShippingRepository shippingRepository;

    public CheckoutService(AuthenticationService authenticationService,
                           CartService cartService, CartRepository cartRepository,
                           CustomerRepository customerRepository, OrderRepository orderRepository,
                           ShippingRepository shippingRepository) {
        this.authenticationService = authenticationService;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.shippingRepository = shippingRepository;
    }

    /**
     * Save a customer's shipping information.
     *
     * @param shipping   shipping object
     * @return  a saved shipping entity
     */
    public Shipping saveShippingInformation(Shipping shipping) {
        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());

        List<Cart> carts = cartRepository.findAllByCustomer(customer);

        shipping = new Shipping(shipping.getFirstName(), shipping.getSurname(), shipping.getAddress(),
                shipping.getCity(), shipping.getState(), shipping.getEmail(), shipping.getPhone());
        shipping.setCustomer(customer);

        for (int i = 0; i < carts.size(); i++) {
            carts.get(i).setShipping(shipping);
        }

        shippingRepository.save(shipping);
        cartRepository.saveAll(carts);

        return shipping;
    }

    /**
     * Save customer orders by transferring product information from cart to orders and emptying
     * cart.
     *
     * @param reference transaction reference number generated for payment gateway.
     * @return list of ordered made.
     */
    public List<Orders> saveCustomerOrders(String reference) {
        List<Orders> orders = new ArrayList<>();

        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        List<Cart> carts = cartRepository.findAllByCustomer(customer);
        carts.forEach(cart -> {
            Orders order = new Orders(cart.getProductId(), cart.getProduct(),
                    cart.getProductImageUrl(), cart.getQuantity(), cart.getPrice(), reference);
            order.setCustomer(customer);
            order.setShipping(cart.getShipping());

            orders.add(order);
        });

        orderRepository.saveAll(orders);
        cartService.deleteAllCart();

        return orders;
    }
}
