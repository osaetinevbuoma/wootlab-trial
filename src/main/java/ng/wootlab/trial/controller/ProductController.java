package ng.wootlab.trial.controller;

import ng.wootlab.trial.exception.ProductNotFoundException;
import ng.wootlab.trial.model.Product;
import ng.wootlab.trial.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Secured("ROLE_USER")
@RequestMapping(value = "/products")
public class ProductController {
    private final static Logger log = Logger.getLogger(ProductController.class.getName());

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(@RequestParam("page") Optional<Integer> page, Model model) {
        int currentPage = page.orElse(1);
        int itemsPerPage = 8;

        Page<Product> products = productService.listProducts(PageRequest.of(currentPage - 1,
                itemsPerPage));
        model.addAttribute("products", products);

        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "product/products";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable("id") int id, Model model) throws ProductNotFoundException {
        Optional<Product> product = productService.getProductDetails(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("The product you are looking for does not exist.");
        }

        model.addAttribute("product", product.get());
        return "product/details";

    }
}
