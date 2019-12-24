package ng.wootlab.trial.service;

import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.model.Product;
import ng.wootlab.trial.repository.CartRepository;
import ng.wootlab.trial.repository.CustomerRepository;
import ng.wootlab.trial.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
@PreAuthorize("authenticated")
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final AuthenticationService authenticationService;

    private final static Logger log = Logger.getLogger(CartService.class.getName());

    public CartService(CartRepository cartRepository, CustomerRepository customerRepository,
                       ProductRepository productRepository,
                       AuthenticationService authenticationService) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.authenticationService = authenticationService;
    }

    /**
     * Get list of products in customer's cart.
     *
     * @return list of cart objects belonging to customer.
     */
    public List<Map<String, Object>> getCustomersCart() {
        List<Map<String, Object>> cartList = new ArrayList<>();

        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        List<Cart> carts = cartRepository.findAllByCustomer(customer);
        carts.forEach(cart -> {
            Map<String, Object> cartMap = new HashMap<>();
            cartMap.put("id", cart.getId());
            cartMap.put("product_id", cart.getProductId());
            cartMap.put("product", cart.getProduct());
            cartMap.put("product_image_url", cart.getProductImageUrl());
            cartMap.put("price", cart.getPrice());
            cartMap.put("quantity", cart.getQuantity());

            cartList.add(cartMap);
        });

        return cartList;
    }

    /**
     * Add a selected product to the customer's cart.
     * In order to avoid making several calls to the DB after an addition to check if an attempt was
     * made to add an item already existing in cart or not, a map is returned with two values:
     * 1. a cart object
     * 2. a boolean value indicating if a new product was added to cart or if the customer made an
     * attempt to add a product that is already in cart.
     *
     * @param productId id of product to be added to cart
     * @param quantity quantity of product to add to cart
     * @return a map of cart object and boolean indicating if a new product was added to cart
     */
    public Map<String, Object> addToCart(int productId, int quantity) {
        Map<String, Object> map = new HashMap<>();

        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            return null;
        }

        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        Cart cart;
        Optional<Cart> cartOptional = cartRepository.findByProductIdAndCustomer(product.get().getId(),
                customer);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
            map.put("is_new", false);
        } else {
            cart = new Cart(product.get().getId(), product.get().getName(), product.get()
                    .getImageUrl(), product.get().getPrice(), quantity);
            cart.setCustomer(customer);
            cartRepository.save(cart);
            map.put("is_new", true);
        }

        map.put("cart", cart);

        return map;
    }

    /**
     * Update quantity of customer's product in cart. The cart object must match that of customer.
     *
     * @param cartId    cart id
     * @param quantity  quantity of product to update
     * @return  the updated cart object or null if conditions are not met
     */
    public Cart updateProductQuantity(int cartId, int quantity) {
        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        Optional<Cart> cart = cartRepository.findByIdAndCustomer(cartId, customer);
        if (!cart.isPresent()) {
            return null;
        }

        cart.get().setQuantity(quantity);
        cartRepository.save(cart.get());

        return cart.get();
    }

    /**
     * Update all product quantities in customer's cart.
     *
     * @param data list containing product cart information in customer's cart.
     */
    public void updateProductQuantity(List<?> data) {
        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        data.forEach(cart -> {
            Map map =  (Map) cart;
            Optional<Cart> cartOptional = cartRepository.findByIdAndCustomer(
                    Integer.parseInt(map.get("cart_id").toString()), customer);
            if (cartOptional.isPresent()) {
                cartOptional.get().setQuantity(Integer.parseInt(map.get("quantity").toString()));
                cartRepository.save(cartOptional.get());
            }
        });
    }

    /**
     * Delete single cart entity.
     *
     * @param cartId id of cart object
     */
    public void deleteCart(int cartId) {
        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        Optional<Cart> cart = cartRepository.findByIdAndCustomer(cartId, customer);
        if (!cart.isPresent()) {
            return;
        }

        cartRepository.delete(cart.get());
    }

    /**
     * Empty customer's cart.
     */
    public void deleteAllCart() {
        Customer customer = customerRepository.getOne(authenticationService
                .getAuthenticatedCustomer().getId());
        List<Cart> carts = cartRepository.findAllByCustomer(customer);
        cartRepository.deleteAll(carts);
    }
}
