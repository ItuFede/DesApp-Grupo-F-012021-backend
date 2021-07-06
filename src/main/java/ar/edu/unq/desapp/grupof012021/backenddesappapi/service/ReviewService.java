package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import org.springframework.web.client.HttpClientErrorException;

public interface ReviewService {
    Review getReviewById(long reviewId) throws HttpClientErrorException.NotFound;

    void upvoteReview(long reviewId);

    void downvoteReview(long reviewId);

    Review createTemporalReview(ReviewDTO reviewDTO, Media media);

    Review createReview(ReviewDTO reviewDTO, String accessToken);
}
