package ng.wootlab.trial;

import ng.wootlab.trial.model.Category;
import ng.wootlab.trial.model.Image;
import ng.wootlab.trial.model.Product;
import ng.wootlab.trial.repository.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DBInitializer {
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final VideoRepository videoRepository;

    public DBInitializer(CategoryRepository categoryRepository, ImageRepository imageRepository,
                         ProductRepository productRepository, ReviewRepository reviewRepository,
                         VideoRepository videoRepository) {
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.videoRepository = videoRepository;
    }

    /*public void initializeDatabase() {
        List<Category> categories = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        // Create product categories
        categories.add(new Category("Electronics"));
        categories.add((new Category("Fashion")));
        categories.add(new Category("Food"));
        categories.add(new Category("Jewelry"));
        categories.add(new Category("Toys"));
        categoryRepository.saveAll(categories);

        *//*
         * Create products and their details
         *//*
        for (int i = 0; i < 20; i++) {
            // Product objects.
            List<Category> categoryList = categoryRepository.findAll();
            Category category = categoryList.get((int) generateRandomNumber(
                    categoryList.size() - 1, 0));

        }
    }*/

    private double generateRandomNumber(int max, int min) {
        return (Math.random() * ((max - min) + 1)) + min;
    }
}
