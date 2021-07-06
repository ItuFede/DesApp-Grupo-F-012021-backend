package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReviewRanking;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.UserEntityRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final UserEntityRepository userEntityRepository;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    public ReviewServiceImpl(ReviewRepository reviewRepositoryMock, UserEntityRepository userEntityRepository, JwtTokenUtil jwtTokenUtil) {
        this.reviewRepository = reviewRepositoryMock;
        this.userEntityRepository = userEntityRepository;
        this.jwtTokenUtil = jwtTokenUtil;
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

    @Override
    public Review createTemporalReview(ReviewDTO reviewDTO, Media media) {
        Review review = new Review();
        review.setShortText(reviewDTO.shortText);
        review.setLongText(reviewDTO.longText);
        review.setOriginalPlatform(reviewDTO.originalPlatform);
        review.setLanguage(reviewDTO.language);
        review.setPremium(reviewDTO.isPremium);
        review.setHasSpoilers(reviewDTO.hasSpoilers);
        review.setRegion(reviewDTO.region);
        review.setMediaReview(media);

        return review;
    }

    @Override
    public Review createReview(ReviewDTO reviewDTO, String accessToken) {
        String username = jwtTokenUtil.getUsernameFromToken(accessToken.replace("Bearer ", ""));
        UserEntity userEntity = userEntityRepository.findByUsername(username);

        Review review = new Review(
                reviewDTO.shortText,
                reviewDTO.longText,
                reviewDTO.originalPlatform,
                reviewDTO.language,
                reviewDTO.isPremium,
                reviewDTO.hasSpoilers,
                reviewDTO.region,
                reviewDTO.score,
                null,
                userEntity
        );

        return review;
    }
}
