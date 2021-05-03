package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;

public interface ReviewService
{
    Review getReviewById(long reviewId);

    void upvoteReview(Review review);

    void downvoteReview(Review review);
}
