package ng.wootlab.trial;

import ng.wootlab.trial.model.*;
import ng.wootlab.trial.repository.*;
import ng.wootlab.trial.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductUseCaseTests {

    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ImageRepository imageRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private VideoRepository videoRepository;

    @Autowired TestEntityManager testEntityManager;

    private ProductService productService;
    private Utilities utilities;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);

        utilities = new Utilities(testEntityManager);
        // Create categories
        utilities.createProductCategories();

        // Create products
        Category category = categoryRepository.findByCategory("Toys").get();
        utilities.createProducts(category);
    }

    @AfterEach
    void destroy() {
        utilities.destroyDB();
    }

    @Test
    void testProductListing() {
        Page<Product> products = productService.listProducts(PageRequest.of(0, 3));
        Assertions.assertThat(products.getTotalElements()).isEqualTo(3L);
    }

    @Test
    void testProductDetails() {
        Page<Product> products = productService.listProducts(PageRequest.of(0, 3));
        Optional<Product> product = productService.getProductDetails(products.get().findFirst()
                .get().getId());
        Assertions.assertThat(products.get().findFirst().get().getName())
                .isEqualTo(product.get().getName());
    }

    @Test
    void testProductImages() {
        Page<Product> products = productService.listProducts(PageRequest.of(0, 3));
        Optional<Product> product = productService.getProductDetails(products.get().findFirst()
                .get().getId());
        Assertions.assertThat(product.isPresent()).isTrue();

        List<Image> images = imageRepository.findAllByProduct(product.get());
        Assertions.assertThat(images.size()).isGreaterThan(0);
        Assertions.assertThat(images.size()).isEqualTo(3);

        Image image = imageRepository.getOne(images.get(0).getId());
        Assertions.assertThat(images.get(0).getUrl()).isEqualTo(image.getUrl());
    }

    @Test
    void testProductVideos() {
        Page<Product> products = productService.listProducts(PageRequest.of(0, 3));
        Optional<Product> product = productService.getProductDetails(products.get().findFirst()
                .get().getId());
        Assertions.assertThat(product.isPresent()).isTrue();

        List<Video> videos = videoRepository.findAllByProduct(product.get());
        Assertions.assertThat(videos.size()).isGreaterThan(0);
        Assertions.assertThat(videos.size()).isEqualTo(3);

        Video video = videoRepository.getOne(videos.get(0).getId());
        Assertions.assertThat(videos.get(0).getUrl()).isEqualTo(video.getUrl());
    }

    @Test
    void testProductReviews() {
        Page<Product> products = productService.listProducts(PageRequest.of(0, 3));
        Optional<Product> product = productService.getProductDetails(products.get().findFirst()
                .get().getId());
        Assertions.assertThat(product.isPresent()).isTrue();

        List<Review> reviews = reviewRepository.findAllByProduct(product.get());
        Assertions.assertThat(reviews.size()).isGreaterThan(0);
        Assertions.assertThat(reviews.size()).isEqualTo(3);

        Review review = reviewRepository.getOne(reviews.get(0).getId());
        Assertions.assertThat(reviews.get(0).getReviewer()).isEqualTo(review.getReviewer());
    }
}
