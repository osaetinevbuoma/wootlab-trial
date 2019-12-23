package ng.wootlab.trial.controller;

import ng.wootlab.trial.service.CartService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured("ROLE_USER")
public class PurchaseController {
    private final CartService cartService;

    public PurchaseController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String cart() {
        return "purchase/cart";
    }

    @GetMapping(value = "/cart/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cartList() {
        return ResponseEntity.ok(cartService.getCustomersCart());
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
