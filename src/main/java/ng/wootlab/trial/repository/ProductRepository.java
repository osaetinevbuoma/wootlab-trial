package ng.wootlab.trial.repository;

import ng.wootlab.trial.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Orders, Integer> {
}
