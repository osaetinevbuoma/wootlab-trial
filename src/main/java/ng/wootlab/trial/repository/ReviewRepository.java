package ng.wootlab.trial.repository;

import ng.wootlab.trial.model.Product;
import ng.wootlab.trial.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByProduct(Product product);
}
