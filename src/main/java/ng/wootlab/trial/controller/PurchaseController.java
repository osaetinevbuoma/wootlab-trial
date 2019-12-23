package ng.wootlab.trial.controller;

import ng.wootlab.trial.exception.CartNotFoundException;
import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@Controller
@Secured("ROLE_USER")
public class PurchaseController {
    private static final Logger log = Logger.getLogger(PurchaseController.class.getName());

    private final CartService cartService;

    public PurchaseController(CartService cartService) {
        this.cartService = cartService;
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
            throws CartNotFoundException {
        if (null == data.get("product_id")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing product ID");
        }

        int productId = Integer.parseInt(data.get("product_id").toString());
        Map<String, Object> resultMap = cartService.addToCart(productId);
        if (resultMap.get("cart") == null) {
            throw new CartNotFoundException("Selected product does not exist.");
        }

        return ResponseEntity.ok(resultMap.get("is_new"));
    }

    @PutMapping(value = "/cart/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCartProductQuantity(@RequestBody Map<String, Object> data)
            throws CartNotFoundException {
        if (null == data.get("cart_id") || null == data.get("quantity")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing cart ID or quantity.");
        }

        int cartId = Integer.parseInt(data.get("cart_id").toString());
        int quantity = Integer.parseInt(data.get("quantity").toString());

        Cart cart = cartService.updateProductQuantity(cartId, quantity);
        if (cart == null) {
            throw new CartNotFoundException("Cart does not exist.");
        }

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
    public String checkout() {
        return "purchase/checkout";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "purchase/confirmation";
    }
}
