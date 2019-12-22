package ng.wootlab.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @GetMapping
    public String products() {
        return "product/products";
    }

    @GetMapping("/details")
    public String details() {
        return "product/details";
    }
}
