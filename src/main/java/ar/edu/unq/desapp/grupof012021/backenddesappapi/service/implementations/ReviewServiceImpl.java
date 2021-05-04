package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReviewRanking;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import java.util.List;

@Service("reviesService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(){}

    public ReviewServiceImpl(ReviewRepository reviewRepositoryMock) {
        this.reviewRepository = reviewRepositoryMock;
    }

    public Review getReviewById(long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    //For now we add N'times positive votes until we have the user id.
    @Override
    public void upvoteReview(long reviewId) {
        Review review = this.getReviewById(reviewId);
        List<ReviewRanking> reviewsRanking = review.getReviewRankings();
        reviewsRanking.add(new ReviewRanking(true));
        reviewRepository.save(review);
    }

    @Override
    public void downvoteReview(long reviewId) {
        Review review = this.getReviewById(reviewId);
        List<ReviewRanking> reviewsRanking = review.getReviewRankings();
        reviewsRanking.add(new ReviewRanking(false));
        reviewRepository.save(review);
    }
}