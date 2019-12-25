package ng.wootlab.trial;

import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.model.Category;
import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.model.Product;
import ng.wootlab.trial.repository.CartRepository;
import ng.wootlab.trial.repository.CategoryRepository;
import ng.wootlab.trial.repository.CustomerRepository;
import ng.wootlab.trial.repository.ProductRepository;
import ng.wootlab.trial.security.WithMockCustomer;
import ng.wootlab.trial.service.AuthenticationService;
import ng.wootlab.trial.service.CartService;
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
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
@WithMockCustomer
public class CartUseCaseTests {
    @Autowired private CartRepository cartRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private ProductRepository productRepository;

    @Autowired
    TestEntityManager testEntityManager;

    private AuthenticationService authenticationService;
    private CartService cartService;
    private Utilities utilities;

    private static final Logger log = Logger.getLogger(CartUseCaseTests.class.getName());

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(customerRepository);
        cartService = new CartService(cartRepository, customerRepository, productRepository,
                authenticationService);

        utilities = new Utilities(testEntityManager);

        // Create categories
        utilities.createProductCategories();

        // Create products
        Category category = categoryRepository.findByCategory("Toys").get();
        utilities.createProducts(category);

        // Create a customer
        utilities.createCustomer();

        // Retrieve created customer and create cart
        Customer customer = customerRepository.findByEmail(Utilities.EMAIL);
        utilities.createCart(customer);
    }

    @Test
    void testCustomersCartUseCases() {
        /*
         * Test adding fetching customer's cart
         */
        List<Map<String, Object>> cartList = cartService.getCustomersCart();
        Assertions.assertThat(cartList.size()).isEqualTo(1);
        Assertions.assertThat(cartList.get(0).get("product")).isEqualTo(Utilities.PRODUCT_NAMES[0]);

        /*
         * Test adding product to cart.
         */
        Optional<Product> product = productRepository.findByName(Utilities.PRODUCT_NAMES[0]);
        Assertions.assertThat(product.isPresent()).isTrue();

        Map<String, Object> cartMap = cartService.addToCart(product.get().getId(), 3);
        List<Cart> cartItems = cartRepository.findAll();
        Assertions.assertThat(cartList.size()).isEqualTo(1);

        Cart cart = cartItems.get(0);
        Assertions.assertThat(cart).isEqualTo(cartMap.get("cart"));

        /*
         * Test updating product quantity in cart.
         */
        cart = cartService.updateProductQuantity(cart.getId(), 10);
        Assertions.assertThat(cart.getQuantity()).isEqualTo(10);

        /*
         * Test deleting product from cart.
         */
        cartService.deleteCart(cart.getId());
        cartList = cartService.getCustomersCart();
        Assertions.assertThat(cartList.size()).isEqualTo(0);
    }
}
