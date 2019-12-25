package ng.wootlab.trial;

import ng.wootlab.trial.model.*;
import ng.wootlab.trial.repository.*;
import ng.wootlab.trial.security.WithMockCustomer;
import ng.wootlab.trial.service.AuthenticationService;
import ng.wootlab.trial.service.CartService;
import ng.wootlab.trial.service.CheckoutService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
@WithMockCustomer
public class CheckoutUseCaseTests {

    @Autowired private CartRepository cartRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ShippingRepository shippingRepository;

    @Autowired TestEntityManager testEntityManager;

    private AuthenticationService authenticationService;
    private CartService cartService;
    private CheckoutService checkoutService;
    private Utilities utilities;
    private Customer customer;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(customerRepository);
        cartService = new CartService(cartRepository, customerRepository, productRepository,
                authenticationService);
        checkoutService = new CheckoutService(authenticationService,cartService, cartRepository,
                customerRepository, orderRepository, shippingRepository);

        utilities = new Utilities(testEntityManager);

        // Create categories
        utilities.createProductCategories();

        // Create products
        Category category = categoryRepository.findByCategory("Toys").get();
        utilities.createProducts(category);

        // Create a customer
        utilities.createCustomer();

        // Retrieve created customer and create cart
        customer = customerRepository.findByEmail(Utilities.EMAIL);
        utilities.createCart(customer);
    }

    @Test
    void testCheckoutOperations() {
        // Create and save shipping information
        Shipping shipping = new Shipping("John", "Doe", "Address",
                "City", "State", "j.doe@testing.net", "+2341234567");
        shipping.setCustomer(customer);
        Shipping newShippingInfo = checkoutService.saveShippingInformation(shipping);
        Assertions.assertThat(newShippingInfo.getFirstName()).isEqualTo("John");
        Assertions.assertThat(newShippingInfo.getSurname()).isEqualTo("Doe");
        Assertions.assertThat(newShippingInfo.getAddress()).isEqualTo("Address");
        Assertions.assertThat(newShippingInfo.getCity()).isEqualTo("City");
        Assertions.assertThat(newShippingInfo.getState()).isEqualTo("State");
        Assertions.assertThat(newShippingInfo.getEmail()).isEqualTo("j.doe@testing.net");
        Assertions.assertThat(newShippingInfo.getPhone()).isEqualTo("+2341234567");

        // Order operations
        List<Cart> carts = cartRepository.findAllByCustomer(customer);
        int reference = (int) Math.floor((Math.random() * 1000000000) + 1);
        List<Orders> orders = checkoutService.saveCustomerOrders(String.valueOf(reference));
        Assertions.assertThat(orders.size()).isEqualTo(carts.size());

        carts = cartRepository.findAllByCustomer(customer);
        Assertions.assertThat(carts.size()).isEqualTo(0);
    }
}
