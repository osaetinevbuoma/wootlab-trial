package ng.wootlab.trial.repository;

import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByCustomer(Customer customer);
}
