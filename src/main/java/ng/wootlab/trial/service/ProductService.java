package ng.wootlab.trial.service;

import ng.wootlab.trial.model.Product;
import ng.wootlab.trial.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * List marketplace products.
     *
     * @param pageable Pageable object for pagination and sorting
     * @return a pageable list of products
     */
    public Page<Product> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Get details of a selected product.
     *
     * @param id the id of the selected product.
     * @return a product object
     */
    public Optional<Product> getProductDetails(int id) {
        return productRepository.findById(id);
    }
}
