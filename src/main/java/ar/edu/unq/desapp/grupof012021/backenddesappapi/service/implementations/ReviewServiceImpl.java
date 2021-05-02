package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReviewRanking;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //For now we add N'times positive votes until we have the user id.
    @Override
    public void upvoteReview(Review review) {
        List<ReviewRanking> reviewsRanking = review.getReviewRankings();
        reviewsRanking.add(new ReviewRanking(true));
    }

    @Override
    public void downvoteReview(Review review) {
        List<ReviewRanking> reviewsRanking = review.getReviewRankings();
        reviewsRanking.add(new ReviewRanking(false));
    }
}
