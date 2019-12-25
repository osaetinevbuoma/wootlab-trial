package ng.wootlab.trial;

import ng.wootlab.trial.model.*;
import ng.wootlab.trial.repository.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class Utilities {

    private TestEntityManager testEntityManager;

    public static final String EMAIL = "j.doe@testing.net";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "John";
    public static final String SURNAME = "Doe";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String[] PRODUCT_NAMES = { "Product_1", "Product_2", "Product_3" };
    public static final String[] IMAGE_URLS = { "/images/url_1.jpg", "/images/url_2.jpg",
            "/images/url_3.jpg" };
    public static final String[] VIDEO_URLS = { "/video/url_1.jpg", "/video/url_2.jpg",
            "/video/url_3.jpg" };
    public static final String[] REVIEWER_NAMES = { "John Doe", "Jane Doe", "Jonathan Doe" };
    public static final String[] REVIEWER_IMAGE_URLS = { "/images/review_image_url_1.jpg",
            "/images/review_image_url_2.jpg", "/images/review_image_url_3.jpg" };

    public Utilities(TestEntityManager testEntityManager) {
        this.testEntityManager = testEntityManager;
    }

    public void createProductCategories() {
        testEntityManager.persist(new Category("Toys"));
        testEntityManager.persist(new Category("Furniture"));
        testEntityManager.persist(new Category("Jewelry"));
        testEntityManager.flush();
    }

    public void createProducts(Category category) {
        for (int i = 0; i < 3; i++) {
            double price = ((i+1)*20000);
            double rating = i+1;
            Product product = new Product(PRODUCT_NAMES[i], price, "Some description",
                    "image_url", rating);
            product.setCategory(category);
            testEntityManager.persist(product);

            for (int j = 0; j < 3; j++) {
                Image image = new Image(IMAGE_URLS[j]);
                image.setProduct(product);

                Video video = new Video(VIDEO_URLS[j]);
                video.setProduct(product);

                double rate = j/2;
                Review review = new Review(REVIEWER_NAMES[j], REVIEWER_IMAGE_URLS[j], rate,
                        "Review");
                review.setProduct(product);

                testEntityManager.persist(review);
                testEntityManager.persist(video);
                testEntityManager.persist(image);
                testEntityManager.flush();
            }
        }
    }

    public void createCustomer() {
        Role role = new Role(ROLE_USER);

        String password = new BCryptPasswordEncoder().encode(PASSWORD);
        Customer customer = new Customer(FIRST_NAME, SURNAME, EMAIL, password);
        customer.setRole(role);

        testEntityManager.persist(role);
        testEntityManager.persist(customer);
        testEntityManager.flush();
    }

    public void createCart(Customer customer) {
        List<Product> products = testEntityManager.getEntityManager()
                .createQuery("SELECT p FROM Product p").getResultList();
        Cart cart = new Cart(products.get(0).getId(), products.get(0).getName(), products.get(0).getImageUrl(),
                products.get(0).getPrice());
        cart.setCustomer(customer);
        testEntityManager.persist(cart);
        testEntityManager.flush();
    }

    public void destroyDB() {
        testEntityManager.getEntityManager().createQuery("DELETE FROM Shipping");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Cart");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Category");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Customer");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Image");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Orders");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Product");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Review");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Role");
        testEntityManager.getEntityManager().createQuery("DELETE FROM Video");
    }
}
