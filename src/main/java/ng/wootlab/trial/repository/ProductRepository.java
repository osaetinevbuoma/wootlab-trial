package ng.wootlab.trial.repository;

import ng.wootlab.trial.model.Orders;
import ng.wootlab.trial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int id);
}
