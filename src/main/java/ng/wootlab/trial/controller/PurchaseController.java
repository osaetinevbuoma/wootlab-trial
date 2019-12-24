package ng.wootlab.trial.controller;

import ng.wootlab.trial.exception.CartNotFoundException;
import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        int quantity = Integer.parseInt(data.get("quantity").toString());

        Map<String, Object> resultMap = cartService.addToCart(productId, quantity);
        if (resultMap.get("cart") == null) {
            throw new CartNotFoundException("Selected product does not exist.");
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
        return "purchase/checkout";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "purchase/confirmation";
    }

    @GetMapping("/payment")
    public String payment() {
        return "purchase/payment";
    }
}
