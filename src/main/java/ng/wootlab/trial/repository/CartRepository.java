package ng.wootlab.trial.repository;

import ng.wootlab.trial.model.Cart;
import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByCustomer(Customer customer);
    Optional<Cart> findByIdAndCustomer(int id, Customer customer);
    Optional<Cart> findByProductIdAndCustomer(Integer productId, Customer customer);
}
