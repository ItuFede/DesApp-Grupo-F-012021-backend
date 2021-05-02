package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviesService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(){}

    public ReviewServiceImpl(ReviewRepository reviewRepositoryMock) {
        this.reviewRepository = reviewRepositoryMock;
    }

    public Review getReviewById(Long reviewId)
    {
        return reviewRepository.findByIdReview(reviewId);
    }
}
