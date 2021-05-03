package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.dataHelper.ReviewDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ReviewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.Mockito.mock;

public class ReviewServiceTestCase {

    //Services
    private static ReviewService reviewService;
    private static ReviewRepository reviewRepositoryMock;

    //Constants
    private static final Long REVIEW_ID = Long.valueOf(1);

    @BeforeAll
    public static void setUp()
    {
        reviewRepositoryMock = mock(ReviewRepository.class);
        reviewService = new ReviewServiceImpl(reviewRepositoryMock);
    }

    @Test
    public void reviewNotExits_findById_null()
    {
        Review review = reviewService.getReviewById(REVIEW_ID);

        Assertions.assertNull(review);
    }

    @Test
    public void review_findById_getTheReview()
    {
        Review review = ReviewDataHelper.getReview();

        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);

        Assertions.assertNotNull(review);
    }

    @Test
    public void reviewWithoutRanking_upvoteReview_reviewPositive()
    {
        Review review = ReviewDataHelper.getReview();

        reviewService.upvoteReview(review);

        Assertions.assertEquals(review.getReviewRankings().stream().count(), 1);
        Assertions.assertTrue(review.getReviewRankings().get(0).isPositiveVote());
    }

    @Test
    public void reviewWithoutRanking_downvoteReview_reviewNegative()
    {
        Review review = ReviewDataHelper.getReview();

        reviewService.downvoteReview(review);

        Assertions.assertEquals(review.getReviewRankings().stream().count(), 1);
        Assertions.assertFalse(review.getReviewRankings().get(0).isPositiveVote());
    }
}
