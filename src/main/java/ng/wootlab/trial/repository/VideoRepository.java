package ng.wootlab.trial.repository;

import ng.wootlab.trial.model.Product;
import ng.wootlab.trial.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    List<Video> findAllByProduct(Product product);
}
