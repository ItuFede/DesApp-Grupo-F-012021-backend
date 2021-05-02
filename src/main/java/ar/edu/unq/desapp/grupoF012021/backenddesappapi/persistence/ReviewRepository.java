package ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Actor;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>
{
    Review findByIdReview(Long reviewId);
}
