package ng.wootlab.trial;

import ng.wootlab.trial.model.*;
import ng.wootlab.trial.repository.*;
import ng.wootlab.trial.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
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
public class ProductUseCaseTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    private ProductService productService;

    @BeforeEach
    void initService() {
        // Create categories
        testEntityManager.persist(new Category("Toys"));
        testEntityManager.persist(new Category("Furniture"));
        testEntityManager.persist(new Category("Jewelry"));
        testEntityManager.flush();

        // Create products
        Category category = categoryRepository.findByCategory("Toys").get();
        for (int i = 0; i < 3; i++) {
            double price = ((i+1)*20000);
            double rating = i+1;
            Product product = new Product("Product_" + i, price, "Some description",
                    "image_url", rating);
            product.setCategory(category);
            testEntityManager.persist(product);
            testEntityManager.flush();

            for (int j = 0; j < 10; j++) {
                Image image = new Image("image_url_" + j);
                image.setProduct(product);

                Video video = new Video("video_url_" + j);
                video.setProduct(product);

                double rate = j/2;
                Review review = new Review("Reviewer_" + j,
                        "reviewer_image_url_" + j, rate, "Review");
                review.setProduct(product);

                testEntityManager.persist(review);
                testEntityManager.persist(video);
                testEntityManager.persist(image);
                testEntityManager.flush();
            }
        }

        productService = new ProductService(productRepository);
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
        Assertions.assertThat(images.size()).isEqualTo(10);

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
        Assertions.assertThat(videos.size()).isEqualTo(10);

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
        Assertions.assertThat(reviews.size()).isEqualTo(10);

        Review review = reviewRepository.getOne(reviews.get(0).getId());
        Assertions.assertThat(reviews.get(0).getReviewer()).isEqualTo(review.getReviewer());
    }
}
