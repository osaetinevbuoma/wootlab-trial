package ng.wootlab.trial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {

    @GetMapping("/cart")
    public String cart() {
        return "purchase/cart";
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
