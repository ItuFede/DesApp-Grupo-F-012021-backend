package ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>
{
    Review findById(long reviewId);
}
