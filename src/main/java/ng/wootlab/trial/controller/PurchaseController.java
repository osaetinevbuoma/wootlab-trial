package ng.wootlab.trial.controller;

import ng.wootlab.trial.auth.AuthenticatedCustomer;
import ng.wootlab.trial.exception.CartNotFoundException;
import ng.wootlab.trial.exception.ProductNotFoundException;
import ng.wootlab.trial.model.Orders;
import ng.wootlab.trial.model.Shipping;
import ng.wootlab.trial.service.AuthenticationService;
import ng.wootlab.trial.service.CheckoutService;
import ng.wootlab.trial.service.CartService;
import ng.wootlab.trial.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@Secured("ROLE_USER")
public class PurchaseController {
    private static final Logger log = Logger.getLogger(PurchaseController.class.getName());

    private final AuthenticationService authenticationService;
    private final CheckoutService checkoutService;
    private final CartService cartService;
    private final EmailService emailService;

    public PurchaseController(AuthenticationService authenticationService,
                              CheckoutService checkoutService, CartService cartService,
                              EmailService emailService) {
        this.authenticationService = authenticationService;
        this.checkoutService = checkoutService;
        this.cartService = cartService;
        this.emailService = emailService;
    }

    @GetMapping("/cart")
    public String cart() {
        return "purchase/cart";
    }

    @GetMapping(value = "/cart/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cartList() {
        return ResponseEntity.ok(cartService.getCustomersCart());
    }

    @PostMapping(value = "/cart/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> data)
            throws ProductNotFoundException {
        if (null == data.get("product_id")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing product ID");
        }

        int productId = Integer.parseInt(data.get("product_id").toString());
        int quantity = Integer.parseInt(data.get("quantity").toString());

        Map<String, Object> resultMap = cartService.addToCart(productId, quantity);
        if (resultMap.get("cart") == null) {
            throw new ProductNotFoundException("Selected product does not exist.");
        }

        return ResponseEntity.ok(resultMap.get("is_new"));
    }

    @PutMapping(value = "/cart/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCartProductQuantity(@RequestBody Map<String, Object> data) {
        List<?> cartData = (List<?>) data.get("data");
        cartService.updateProductQuantity(cartData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/cart/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCart(@PathVariable("id") String cartId) {
        if (cartId.equals("all")) {
            cartService.deleteAllCart();;
        } else {
            cartService.deleteCart(Integer.parseInt(cartId));
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        List<Map<String, Object>> items = cartService.getCustomersCart();
        double total = items
                .stream()
                .mapToDouble(item -> (int) item.get("quantity") * (double) item.get("price"))
                .sum();

        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("shipping", new Shipping());

        return "purchase/checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute @Valid Shipping shipping,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "purchase/checkout";
        }

        shipping = checkoutService.saveShippingInformation(shipping);

        return "redirect:/payment";
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        List<Map<String, Object>> items = cartService.getCustomersCart();
        double total = items
                .stream()
                .mapToDouble(item -> (int) item.get("quantity") * (double) item.get("price"))
                .sum();

        AuthenticatedCustomer customer = authenticationService.getAuthenticatedCustomer();
        int reference = (int) Math.floor((Math.random() * 1000000000) + 1);

        model.addAttribute("paymentTotal", total * 100);
        model.addAttribute("customer", customer);
        model.addAttribute("reference", reference);

        return "purchase/payment";
    }

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrders(@RequestBody Map<String, Object> data) {
        if (null == data.get("reference")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transaction reference missing");
        }

        List<Orders> orders = checkoutService.saveCustomerOrders(data.get("reference").toString());
        if (orders.size() == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Send order confirmation email to customer
        emailService.sendOrderConfirmationEmail(orders);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "purchase/confirmation";
    }
}
