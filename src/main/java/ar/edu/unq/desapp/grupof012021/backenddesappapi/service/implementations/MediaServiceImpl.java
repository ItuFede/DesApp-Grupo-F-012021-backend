package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.MediaRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("mediaService")
public class MediaServiceImpl implements MediaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MediaRepository repository;

    public MediaServiceImpl() { }

    public MediaServiceImpl(MediaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addReviewTo(long mediaId, Review aReview) {
        Media aMedia = this.findById(mediaId);
        aReview.setMediaReview(aMedia);
        aMedia.getReviews().add(aReview);
        repository.save(aMedia);
    }

    @Override
    public List<Review> findAllReviewsFrom(long idMedia) {
        Media aMedia = this.findById(idMedia);
        return aMedia.getReviews();
    }

    @Override
    public List<Review> findAllReviewsFilter(ReviewDTO reviewDTO, long idMedia) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> cq = cb.createQuery(Review.class);

        Root<Review> reviewRoot = cq.from(Review.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(reviewRoot.get("mediaReview"), idMedia));

        if (reviewDTO.isPremium){
            predicates.add(cb.isTrue(reviewRoot.get("isPremium")));
        }
        if (reviewDTO.originalPlatform != null){
            predicates.add(cb.equal(reviewRoot.get("originalPlatform"), reviewDTO.originalPlatform));
        }
        if (reviewDTO.region != null){
            predicates.add(cb.equal(reviewRoot.get("region"), reviewDTO.region));
        }
        if (reviewDTO.language != null){
            predicates.add(cb.equal(reviewRoot.get("language"), reviewDTO.language));
        }
        if (reviewDTO.hasSpoilers){
            predicates.add(cb.isTrue(reviewRoot.get("hasSpoilers")));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        Query query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    public Media findById(long mediaId) {
        return repository.findById(mediaId);
    }
}
