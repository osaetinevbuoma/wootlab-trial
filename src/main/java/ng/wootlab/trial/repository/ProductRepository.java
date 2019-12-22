package ng.wootlab.trial.repository;

import ng.wootlab.trial.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Order, Integer> {
}
